var url = 'http://172.16.223.185:19200/equipment/unit/_search';
//var url = 'http://127.0.0.1:9200/equipment/unit/_search';
var postEditUrl = "";
        $(document).ready(function(){
            $("#getbyid").click(function(){
                getQueryCondition();
                search();
            });
            $("#queryHighlight").click(function(){
                getQueryConditionHighlight();
                searchHighlight();
            });
            $("#editButton").click(function(){
                getEditCondition();
                edit();
            });
            $("#getChildrenButton").click(function(){
                getQueryConditionGetChildren();
                searchGetChildren();
            });


            //getQueryCondition();
            //search();

            // getQueryConditionHighlight();
            // searchHighlight();

            // getQueryConditionGetChildren();
            // searchGetChildren();

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
            query = JSON.parse('{"query":{"match_phrase":{"id":"'+v+'"}}}');
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
function getQueryConditionHighlight() {
    query ="";
    var name = $("#inputHighlight").val();
    console.log("name="+name);
    console.log('{"query":{"match_phrase":{"name":"'+name+'"},"highlight":{"fields":{"name":{}}}}}');
    query = JSON.parse('{"size" : 100,"query":{"match_phrase":{"name":"'+name+'"}},"highlight":{"fields":{"name":{}}}}');
    console.log("query="+query);
}

/**
 * 根据名称高亮查询
  */
function searchHighlight(){    
    $.ajax({
        type:"post",
        url: url,
        crossDomain:true,
        async:false,
        dataType:"json",
        data:JSON.stringify(query),
        success:function(data){
            // console.log(data);
            // console.log(data.hits);
            console.log("length="+JSON.stringify(data.hits));
            var hits = data.hits;
            console.log("长度="+hits.hits.length)
            $("#outputHighlight").html("");
            for(var i=0;i<hits.hits.length;i++){
                $("#outputHighlight").append(hits.hits[i].highlight.name[0]+"<br>");
                console.log(hits.hits[i].highlight.name);

            }
            if(hits.hits.length){
                // console.log(hits.hits.length)
                // console.log("hits="+hits.total+hits.hits[0]._source.name);
                // $("#outputHighlight").html(hits.hits[0]._source.highlight)
                // for(var i=0;i<hits.hits.length;i++){
                //     $("#outputHighlight").html(hits.hits[0]._source.highlight)
                // }
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
    url = "http://127.0.0.1:9200/equipment/unit/"+$("#edit_index_id").val()+"/_update"
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

            console.log(data);
            console.log(data.hits);
            console.log("length="+JSON.stringify(data.hits));
            var hits = data.hits;

        },
        error:function(){
            alert("error");
            $("#outputid").html("")

        }
    })
}

/**
 * 获取孩子
 */
function getQueryConditionGetChildren() {
    query ="";
    var name = $("#inputHighlight").val();
    console.log("name="+name);
    console.log('{"query":{"match_phrase":{"name":"'+name+'"},"highlight":{"fields":{"name":{}}}}}'); 
    query = JSON.parse('{"size" : 100,"query" : {"bool" : {"filter" : {"term" : {"pid" : "100"}}}}}');
    console.log("query="+query);
}

function searchGetChildren(){
    $.ajax({
            type:"post",
            url: url,
            crossDomain:true,
            async:false,
            dataType:"json",
            data:JSON.stringify(query),
            success:function(data){                
                var hits = data.hits;
                if(hits.hits.length){                    
                    for(var i=0;i<hits.hits.length;i++){
                        var obj = hits.hits[i]._source;
                        $("#outputGetChildren").append("第"+i+"个:pid="+obj.pid+";name="+obj.name+"; id= "+obj.id+"<br>");                        
                    }
                }else{
                    alert("没有查到对应ID 设备");
                }
            },
            error:function(){
                alert("error");
                $("#outputGetChildren").html("")

            }
        })
        }

          