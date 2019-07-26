//判断是否为12位数字
function Number12(value) {
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
    if (identity == '管理员') {
        return true;
    } else {
        if (id.charAt(0) == '5' && identity == '学生') {
            return true;
        } else if (id.charAt(0) == '1' && identity == '教师') {
            return true;
        } else {
            return false;
        }
    }

}

function isValidBirthday(val) {
    var pattern = /^(19|20)\d{2}\-((0?[1-9])|(1[0-2]))\-((0?[1-9])|([1-2]\d)|3[01])$/;
    if (pattern.test(val)) {
        var date = new Date(val);
        if (date < new Date("1919-12-31") || date > new Date()) {
            return false;
        }
        var month = val.substring(val.indexOf("-") + 1, val.lastIndexOf("-"));
        return date && (date.getMonth() + 1 == parseInt(month));
    }
    return false;
}