function UBBImage(ImageID) {
    if (ImageID.width > 550) {
        ImageID.width = 550;
        ImageID.height = (ImageID.height * 550) / ImageID.width;
    }
}
function SelectAll(obj) {
    var CkeckType = document.getElementsByName("SelectID");
    for (i = 0; i < CkeckType.length; i++) {
        CkeckType[i].checked = obj.checked;
    }
}
function CheckSelect() {
    var aspnetForm = document.getElementsByName("SelectID");
    var checked = false;
    if (aspnetForm != null) {
        for (i = 0; i < aspnetForm.length; i++) {
            if (aspnetForm[i].checked == true) {
                checked = true;
            }
        }
    }
    if (checked == false) {
        alert("请至少选择一项！");
        return false;
    }
    else {
        if (!confirm("你确认执行此操作？")) {
            return false;
        }
    }
    return true;
}
function ckcode() {
    document.getElementById("ImageCode").src = "" + RootSite + "resources/images/009.gif";
    document.getElementById("ImageCode").src = "" + RootSite + "ShowCode.aspx?Rnd=" + Math.random() + "";
}

