$(document).ready(function(){
	
	// panel单击事件
    $("img[fid=\"click_collapse_id\"]").click(function(){
    	$("div[id=\"" + this.id + "\"]").toggle();
    });
    
    // stable标题单击事件
    $("img[ftype=\"img_click_collapse_stable\"]").click(function(){
    	$("div[ftype=\"div_click_collapse_stable\"][fid=\"" + $(this).attr("fid") + "\"]").toggle();
    });
    
    // mtable标题单击事件
    $("img[ftype=\"img_click_collapse_mtable\"]").click(function(){
    	$("div[ftype=\"div_click_collapse_mtable\"][fid=\"" + $(this).attr("fid") + "\"]").toggle();
    });
    
    // 番页单击事件
    $("span[ftype=\"span_click_pageturn\"]").click(function(){
    	var fid = $(this).attr("fid");
    	if (!isNull(fid) && !isNull($("#" + fid).val())) {
    		location.href = $("#" + fid).val();
    	}
    });
    // 番页单击事件
    $("span[ftype=\"span_click_pageturn_cus\"]").click(function(){
    	var fid = $(this).attr("fid");
    	if (!isNull(fid) && !isNull($("#" + fid).val()) && !isNull($("#p" + fid).val()) && !isNull($("#t" + fid).val())) {
    		var page = $("#p" + fid).val();
    		var total = $("#t" + fid).val();
    		var reg = /^[0-9]+$/;
    		if (!reg.test(page)) {
    			alert("页数应为整数");
    			return;
    		}
    		page = parseInt(page);
    		total = parseInt(total);
    		if (page > total) {
    			alert("页数不能大于总页数");
    			return;
    		}
    		location.href = $("#" + fid).val() + page;
    	}
    });
    
});

// 判空
function isNull(object) {
	if (object == null || object == "" || typeof object == "undefined")
		return true;
	else
		return false;
}