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
        url:"../../../api/tree/v1/getNodes?pid=pddkx_zsbyqGroup@SBID00000026ECABF0AED648E18F470B48C0DD4F4D",
        autoParam:["id", "name=n", "level=lv"],
//                otherParam:{"otherParam":"zTreeAsyncTest"},
        dataFilter: leftTreefilter,
        type: "get"
    },
    check: {
        enable: true
    }
    // ,
    // callback: {
    //     beforeAsync: beforeAsync,
    //     onAsyncSuccess: onAsyncSuccess,
    //     onAsyncError: onAsyncError
    // }
};
