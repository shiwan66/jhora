function leftTreefilter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i=0, l=childNodes.length; i<l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}


var leftTreeSetting = {
    async: {
        enable: true,
               url:"../../../api/tree/v1/getNodes",
        autoParam:["id", "name=n", "level=lv"],
//                otherParam:{"otherParam":"zTreeAsyncTest"},
        dataFilter: leftTreefilter,
        type: "get"
    }
    // ,
    // callback: {
    //     beforeAsync: beforeAsync,
    //     onAsyncSuccess: onAsyncSuccess,
    //     onAsyncError: onAsyncError
    // }
};
