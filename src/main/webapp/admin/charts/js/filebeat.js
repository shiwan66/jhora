var url = 'http://172.16.221.59:19200/filebeat-2017.11.29/_search';
var postEditUrl = "";
        $(document).ready(function(){
            $("#getbyid").click(function(){
                getQueryCondition();
                search();
            });
            // API 查询按钮点击事件
            $("#apiButton").click(function(){
                getAPISearchCondition();
                APISearch();
            });
            $("#editButton").click(function(){
                getEditCondition();
                edit();
            });
        })

        //静态的查询格式
        var query =
            {"query":{
             "match_phrase":{
                 "id":"4x28816d328cc1c5x1428cccccbcxxxb"
             }
             }
             };

/**
 * 获取查询条件
 */
function getQueryCondition() {
            query ="";
            var v = $("#inputId").val();
            console.log("v="+v);
            query = JSON.parse('{"query":{"match_phrase":{"message":"'+v+'"}}}');
            console.log("query="+query);
        }

/**
 * 根据ID获取设备名称
 */
function search(){
    $.ajax({
            type:"post",
            url: url,
            crossDomain:true,
            async:false,
            dataType:"json",
            data:JSON.stringify(query),
            success:function(data){
                console.log(data);
                console.log(data.hits);
                console.log("length="+JSON.stringify(data.hits));
                var hits = data.hits;
                if(hits.hits.length){
                    console.log(hits.hits.length)
                    console.log("hits="+hits.total+hits.hits[0]._source.name);
                    $("#outputid").html(hits.hits[0]._source.name+"; _id= "+hits.hits[0]._id)
                }else{
                    alert("没有查到对应ID 设备");
                }
            },
            error:function(){
                alert("error");
                $("#outputid").html("")

            }
        })
        }

/**
 * 高亮查询条件封装
 */
function getAPISearchCondition() {
    query ="";
    var name = $("#inputAPIValue").val();
    console.log("name="+name);
    query = JSON.parse('{"query":{"match_phrase":{"message":"'+name+'"}}}');
    console.log("query="+query);
}

/**
 * 根据名称高亮查询
  */
function APISearch(){
    var access_token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTUxNzU1ODQ5M30.ImIdKGyMic3FVm-KIhkt4PTqkgC9r8gb26hP8PqJsZvP6Uf86mDAWMLG_MxbR5JyUsyVSyN91HH0mhKfEkwdyQ";
    var url = 'http://localhost:8080/api/monV3/searchIndex?param=123';
    $.ajax({
        type:"post",
        url: url,
        headers: {
            'Authorization':'Bearer '+access_token,
            'Content-Type':'text/plain'
        },
        crossDomain:true,
        async:false,
        dataType:"json",
        data:JSON.stringify(query),
        success:function(data){
            console.log(data);
            console.log("length="+JSON.stringify(data.hits));
            var hits = data.hits;
            console.log("长度="+hits.hits.length)
            if(hits.hits.length){
                console.log(hits.hits.length)
                $("#apiOutputPid").html("");
                for(var i=0;i<hits.hits.length;i++){
                    console.log(hits.hits[i]._source.message);
                    $("#apiOutputPid").append(hits.hits[i]._source.message+"<br>");
                }
            }else{
                alert("没有查到对应ID 设备");
                $("#outputHighlight").html("")
            }
        },
        error:function(){
            alert("error");
        }
    })
}

/**
 *
 */
function getEditCondition() {
    url = "http://nari_185:9200/equipment/unit/"+$("#edit_index_id").val()+"/_update"
    var v = $("#editName").val();
    console.log("v="+v);
    query = JSON.parse('{"doc" : {"name":"'+v+'"}}');
    console.log("query="+query);
}

/**
 * 编辑设备名称
 */
function edit(){
    $.ajax({
        type:"post",
        url: url,
        crossDomain:true,
        async:false,
        dataType:"json",
        data:JSON.stringify(query),
        success:function(data){
            alert("修改成功 刷新")
            setInterval("location.reload();",1000);
            console.log("length="+JSON.stringify(data.hits));
            var hits = data.hits;
        },
        error:function(){
            alert("error");
            $("#outputid").html("")

        }
    })
}



