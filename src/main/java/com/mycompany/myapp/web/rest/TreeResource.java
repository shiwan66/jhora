package com.mycompany.myapp.web.rest;

import cn.hutool.json.JSONUtil;
import com.aspose.cad.Color;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mycompany.myapp.config.ApplicationProperties;
import com.mycompany.myapp.domain.MxpmsSearchEquipment;
import com.mycompany.myapp.service.MxpmsSearchEquipmentService;
import com.mycompany.myapp.service.dto.MxpmsSearchEquipmentDTO;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
//import com.aspose.cad.imageoptions.PdfOptions;
//import java.awt.Image;

/**
 * Tree controller
 */
@RestController
@RequestMapping("/api/tree")
public class TreeResource {

    private static final CloseableHttpClient httpClient;


    private final Logger log = LoggerFactory.getLogger(TreeResource.class);

    private final MxpmsSearchEquipmentService mxpmsSearchEquipmentService;

    ApplicationProperties applicationProperties;



    public TreeResource(MxpmsSearchEquipmentService mxpmsSearchEquipmentService, ApplicationProperties applicationProperties) {
        this.mxpmsSearchEquipmentService = mxpmsSearchEquipmentService;
        this.applicationProperties = applicationProperties;
    }

    static {
        // httpClient 静态初始化
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }



    /**
    * GET tree
     * 标准树测试数据
    */
    @GetMapping("/v1/getSimpleNodes")
    @Timed
    public String getSimpleNodes() {
        return "[{ id:'01',\tname:'n1',\tisParent:true},{ id:'02',\tname:'n2',\tisParent:false},{ id:'03',\tname:'n3',\tisParent:true},{ id:'04',\tname:'n4',\tisParent:false}]";
    }

    /**
     * 从数据库中取节点
     * @param pid
     * @return
     */
    @GetMapping("/v1/getNodes")
    @Timed
    public String getNodes(@RequestParam("pid") String pid) {
        log.info("从数据库中取节点");
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0, 20, sort);
        Page<MxpmsSearchEquipmentDTO> page = mxpmsSearchEquipmentService.findAll(pageable);
        log.info("page.getSize()="+page.getSize()+"page="+page);
        log.info(page.getContent().size()+"page.getContent()="+page.getContent());

        MxpmsSearchEquipmentDTO[] array = new MxpmsSearchEquipmentDTO[page.getContent().size()];
        page.getContent().toArray(array); // fill the array
        log.info("array="+array.toString());
        String strTreeNodes = page.getContent().toString();
        String[] strArray = new String[page.getContent().size()];
        for(int i=0;i<page.getContent().size();i++){
            String tempName = ((MxpmsSearchEquipmentDTO)array[i]).getName();
            String id = ((MxpmsSearchEquipmentDTO)array[i]).getId().toString();
            strArray[i] = "{ id:'"+id+"',\tname:'"+tempName+"',\tisParent:true}";
        }
//        strTreeNodes = "[{ id:'01',\tname:'n1',\tisParent:true},{ id:'02',\tname:'n2',\tisParent:false},{ id:'03',\tname:'n3',\tisParent:true},{ id:'04',\tname:'n4',\tisParent:false}]";
        strTreeNodes = Arrays.toString(strArray);
        log.info("strTreeNodes="+strTreeNodes);
        return strTreeNodes;
    }


    /**
     * 从缓存中取节点
     * @param pid
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    @GetMapping("/v1/getESNodes")
    @Timed
    public String getESNodes(@RequestParam("pid") String pid) throws UnsupportedEncodingException,IOException {
        {
            String result = null;
            List<MxpmsSearchEquipment> resultList = new ArrayList<MxpmsSearchEquipment>(); // 结果id集合
            String index = "equipment/unit";
            String url = "http://127.0.0.1:9200/" + index + "/_search";
            JSONObject json = new JSONObject();
            ObjectMapper mapper = new ObjectMapper();
//            HttpPost httpPost = new HttpPost(url);
            HttpGet httpGet = new HttpGet(url);
            log.info("pid="+pid);
            String queryTerm = "{\"size\" : 100,\"query\" : {\"bool\" : {\"filter\" : {\"term\" : {\"pid\" : \""+pid+"\"}}}}}";
            log.info("queryTerm="+queryTerm);
            StringEntity stringEntity = new StringEntity(queryTerm);
            stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//            httpPost.setEntity(stringEntity);
            log.info("httpClient="+httpClient+";httpPost="+httpGet);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            cn.hutool.json.JSONArray array = JSONUtil.createArray();
            try {
                int statusCode = response.getStatusLine().getStatusCode();
                if(HttpStatus.OK.value() == statusCode){
                    HttpEntity entity = response.getEntity();
                    if (entity != null){
                        result = EntityUtils.toString(entity, "utf-8");
                        ObjectMapper objectMapper = new ObjectMapper()
                            .configure(SerializationFeature.WRAP_ROOT_VALUE, true);
                        Map<String, Object> map = objectMapper.readValue(result, new TypeReference<Map<String,Object>>(){});
                        log.info("result="+result);
                        log.info("result map="+map);
                        String strHits = (map.get(("hits"))).toString();
                        log.info("result map strHits="+strHits);
                        JSONObject lev1 =  new JSONObject(result);
                        JSONObject parent = (JSONObject) lev1.get("hits");
                        log.info("parent"+parent);
                        JSONArray child1 = (JSONArray) parent.get("hits"); // same for child2
                        log.info("child1 "+child1+child1.length());
                        JSONArray resultJSONArray = new JSONArray();
                        for(int i =0;i<child1.length();i++){
                            JSONObject o = (JSONObject)child1.get(i);
                            JSONObject _sourceObj = (JSONObject)o.getJSONObject("_source");
//                            log.info("child1 "+_sourceObj.get("obj_id"));
                            log.info("child1 名称 "+_sourceObj.get("name"));
                            cn.hutool.json.JSONObject json1 = JSONUtil.createObj();
//                            json1.put("id", _sourceObj.get("id"));
                            json1.put("name", _sourceObj.get("name"));
                            json1.put("obj_id", _sourceObj.get("obj_id"));
                            array.add(json1);
                        }
                    }
                    EntityUtils.consume(entity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return array.toString();
        }
    }

    /**
     * GET tree
     * 仅仅是一个通道
     * 入参
     * {"size" : 100,"query" : {"bool" : {"filter" : {"term" : {"pid" : "pddkx_zsbyqGroup@SBID00000026ECABF0AED648E18F470B48C0DD4F4D"}}}}}
     */
    @PostMapping("/v1/searchIndex")
    @Timed
    public String searchIndexV3(String params,@RequestBody String body) throws JsonParseException,IOException {
        // http://www.baeldung.com/httpclient-post-http-request
        // https://my.oschina.net/u/1270482/blog/212389
        String result = null;
        List<MxpmsSearchEquipment> resultList = new ArrayList<MxpmsSearchEquipment>(); // 结果id集合
        String index = "equipment/unit";
//        String url = "http://172.16.221.59:19200/" + index + "/_search";
        String url = "http://127.0.0.1:9200/" + index + "/_search";
        JSONObject json = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(body);
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(body);
        stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(stringEntity);
        log.info("httpClient="+httpClient+";httpPost="+httpPost);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        cn.hutool.json.JSONArray array = JSONUtil.createArray();
        try {
            int statusCode = response.getStatusLine().getStatusCode();

            if(HttpStatus.OK.value() == statusCode){
                HttpEntity entity = response.getEntity();
                if (entity != null){
                    result = EntityUtils.toString(entity, "utf-8");
                    ObjectMapper objectMapper = new ObjectMapper()
                        .configure(SerializationFeature.WRAP_ROOT_VALUE, true);
//                    ObjectMapper configure = objectMapper.configure(SerializationConfig.Feature.UNWRAP_ROOT_VALUE, true);

                    Map<String, Object> map = objectMapper.readValue(result, new TypeReference<Map<String,Object>>(){});
                    log.info("result="+result);
                    log.info("result map="+map);
                    String strHits = (map.get(("hits"))).toString();
                    log.info("result map strHits="+strHits);


                    JSONObject lev1 =  new JSONObject(result);
                    JSONObject parent = (JSONObject) lev1.get("hits");
                    log.info("parent"+parent);
                    JSONArray child1 = (JSONArray) parent.get("hits"); // same for child2
                    log.info("child1 "+child1+child1.length());
                    JSONArray resultJSONArray = new JSONArray();

                    for(int i =0;i<child1.length();i++){
                        JSONObject o = (JSONObject)child1.get(i);
                        JSONObject _sourceObj = (JSONObject)o.getJSONObject("_source");
                        log.info("child1 "+_sourceObj.get("obj_id"));
                        log.info("child1 名称 "+_sourceObj.get("name"));
                        cn.hutool.json.JSONObject json1 = JSONUtil.createObj();
                        json1.put("id", _sourceObj.get("obj_id"));
                        json1.put("name", _sourceObj.get("name"));
                        array.add(json1);
                    }
                }
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            response.close();
        }
//        return result;
        return array.toString();
    }

    /**
     * 传入IDS
     * @param body
     * { "ids" : "4101,4102,4103,4104"}
     * @return
     * @throws JsonParseException
     * @throws IOException
     */
    @PostMapping("/v1/syncLeftToRight")
    @Timed
    public String syncLeftToRightV1(@RequestBody String body) throws JsonParseException,IOException {
        List<MxpmsSearchEquipment> resultList = new ArrayList<MxpmsSearchEquipment>(); // 结果id集合
        ObjectMapper mapper = new ObjectMapper();
        log.info("body="+body);
        String [] stringArr= body.toString().split(",");
        String index = "equipment/unit";
        String url = "http://"+applicationProperties.getES().getHost()+":"+applicationProperties.getES().getPort()+"/" + index+"/" ;
        HttpPost httpPost = new HttpPost(url);
        log.info("applicationProperties="+applicationProperties.getES().getHost()+";url="+url);
        CloseableHttpResponse response = null;
        int statusCode = 0;
        try {
            for(int i=0;i<stringArr.length;i++){
                String str = stringArr[i];
                str = str.replace("\"", "");
                long num = Long.parseLong(str);
                MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO=mxpmsSearchEquipmentService.findOne(num);
                log.info("stringArr["+i+"]="+str+";num="+num+"；mxpmsSearchEquipmentDTO="+mxpmsSearchEquipmentDTO);
                JSONObject json = new JSONObject();
                json.put("name",mxpmsSearchEquipmentDTO.getName());
                json.put("orgid",mxpmsSearchEquipmentDTO.getOrgid());
                json.put("obj_id",mxpmsSearchEquipmentDTO.getObjId());
                StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");
                log.info("stringEntity="+stringEntity.toString());
                httpPost.setEntity(stringEntity);
                response = httpClient.execute(httpPost);
                statusCode = response.getStatusLine().getStatusCode();
                if(HttpStatus.OK.value() == statusCode){
                    log.info("成功状态statusCode="+statusCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            response.close();
        }
        return statusCode+"";
    }

    @PostMapping("/v1/syncLeftToRightBulk")
    @Timed
    public String syncLeftToRightBulkV1(@RequestBody String body) throws JsonParseException,IOException {
        List<MxpmsSearchEquipment> resultList = new ArrayList<MxpmsSearchEquipment>(); // 结果id集合
        ObjectMapper mapper = new ObjectMapper();
        log.info("body="+body);
        String [] stringArr= body.toString().split(",");
        String index = "equipment/unit";
        String url = "http://"+applicationProperties.getES().getHost()+":"+applicationProperties.getES().getPort()+"/" + index+"/" ;
        HttpPost httpPost = new HttpPost(url);
        log.info("applicationProperties="+applicationProperties.getES().getHost()+";url="+url);
        CloseableHttpResponse response = null;
        int statusCode = 0;
        try {
            for(int i=0;i<stringArr.length;i++){
                String str = stringArr[i];
                str = str.replace("\"", "");
                long num = Long.parseLong(str);
                MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO=mxpmsSearchEquipmentService.findOne(num);
                JSONObject json = new JSONObject();
                json.put("name",mxpmsSearchEquipmentDTO.getName());
                json.put("orgid",mxpmsSearchEquipmentDTO.getOrgid());
                json.put("obj_id",mxpmsSearchEquipmentDTO.getObjId());
                log.info("json.toString()="+json.toString());
                StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");
                httpPost.setEntity(stringEntity);
                response = httpClient.execute(httpPost);
                statusCode = response.getStatusLine().getStatusCode();
                if(HttpStatus.OK.value() == statusCode){
                    log.info("成功状态statusCode="+statusCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            response.close();
        }
        return statusCode+"";
    }

    /**
     * sql 搜索
     * @param params
     * @param body
     * @return
     * @throws JsonParseException
     * @throws IOException
     */
    @PostMapping("/v4/searchIndex")
    @Timed
    public String searchIndexV4(String params,@RequestBody String body) throws JsonParseException,IOException {
        // http://www.baeldung.com/httpclient-post-http-request
        // https://my.oschina.net/u/1270482/blog/212389
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");

        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");

        System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire.header", "debug");

        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");

        String result = null;
        List<MxpmsSearchEquipment> resultList = new ArrayList<MxpmsSearchEquipment>(); // 结果id集合

//      String url = "http://127.0.0.1:9200/_sql?sql=select%20*%20from%20equipment%20limit%203";
        String ual = "http://127.0.0.1:9200/_sql?sql=select%20*%20from%20equipment%20limit%203";
        String query = "select * from equipment limit 3";
        String url = "http://127.0.0.1:9200/_sql?sql="+java.net.URLEncoder.encode(query, "UTF-8").replace("+", "%20");
        log.info("url="+url);
        JSONObject json = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if(HttpStatus.OK.value() == statusCode){
                HttpEntity entity = response.getEntity();
                if (entity != null){
                    result = EntityUtils.toString(entity, "utf-8");
                    log.info("result="+result);
                }
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            response.close();
            log.info("finale response 关闭");
        }
        return result;
    }

    @GetMapping("/v1/exportCADtoImage")
    @Timed
    public void getExportCADtoPng() throws Exception{
        String dir = "src/main/java/com/mycompany/myapp/web/rest/cad/";

        // The path to the resource directory.
//        String dataDir = Utils.getDataDir(ConvertDWGFileToPDF.class) + "DWGDrawings/";
        //ExStart:ConvertDWGFileToPDF
//                String srcFile = dataDir + "Bottom_plate.dwg";
        String srcFile = dir + "ftq-333.dwg";
//                String srcFile = dataDir + "56789.dwg";
//                String srcFile = dataDir + "aaaaa.dwg";


        com.aspose.cad.Image objImage = com.aspose.cad.Image.load(srcFile);

        // Create an instance of CadRasterizationOptions and set its various properties
        CadRasterizationOptions rasterizationOptions = new CadRasterizationOptions();
        rasterizationOptions.setBackgroundColor(Color.getWhite());
        rasterizationOptions.setPageWidth(1600);
        rasterizationOptions.setPageHeight(1600);

        // Create an instance of PdfOptions
        com.aspose.cad.imageoptions.PdfOptions pdfOptions = new com.aspose.cad.imageoptions.PdfOptions();
        // Set the VectorRasterizationOptions property
        pdfOptions.setVectorRasterizationOptions(rasterizationOptions);

        // Export the DWG to PDF
//                objImage.save(dataDir + "Bottom_plate_out_.pdf", pdfOptions);
        objImage.save(dir + "ftq-333.pdf", pdfOptions);
//                objImage.save(dataDir + "56789.pdf", pdfOptions);
//                objImage.save(dataDir + "aaaaa.pdf", pdfOptions);
        //ExEnd:ConvertDWGFileToPDF




    }
}
