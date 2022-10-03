function randStr(len){
    var s = "";
    for(var i=0; i<len; i++){
        s += String.fromCharCode(Math.floor(Math.random()*26)+97);
    }
    return s;
}

const rand = randStr(10);

console.log(rand); // output: "jxqjxqjxqj"