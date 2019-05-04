//判断是否为12位数字
function Number(value) {
    eval("var reg = /\^[0-9]{" + 12 + "\}$/;");
    var re = new RegExp(reg);
    if (re.test(value)) {
        return true;
    }
    else {
        return false;
    }
}

function idWithIdentityValid(id, identity) {
    if (id.charAt(0) == '5' && identity == '学生') {
        return true;
    } else if (id.charAt(0) == '1' && identity == '教师') {
        return true;
    } else {
        return false;
    }
}