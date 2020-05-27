var b=new Array();
var turn=1;
$(function() {
    init()
})

function init() {

    for(i=0;i<15;i++){
        b[i]=new Array(i)
        for(j=0;j<15;j++)
            b[i][j]=-1
    }


    var board=$("#chessboard")
    for(i=0;i<15;i++){
        var row=$('<div class="row"></div>')
        for(j=0;j<15;j++){
            var tile=$('<div class="tile"></div>')
            tile.attr("id",i+"_"+j)
            tile.attr("onclick","play("+i+","+j+")")
            row.append(tile)
        }
        board.append(row)
    }
}

function play(i,j) {
    var d=$("#"+i+"_"+j)
    if(b[i][j]!=-1){
        alert("请选择别的空位下")
    }else {
        var x=$(".current")
        x.removeClass("current")
        if(turn%2==1){
            d.attr("class","tile chess-min current")
            d.append('●')
            b[i][j]=1
        }else {
            d.attr("class","tile chess-max current")
            d.append('○')
            b[i][j]=0
        }
        $.post("/checkerboard",{data:b.toString()},function (data) {
            console.log(data)
            var result=data.result;
            if(data.p!=null){
                var point=data.p;
                var x=$(".current")

                x.removeClass("current")
                i=point.y;
                j=point.x;
                var d=$("#"+i+"_"+j);
                d.attr("class","tile chess-max current");
                d.append('○');
                b[i][j]=0;
                turn+=2;
            }

            if(result==1)
                alert("黑棋胜")
            else if(result==0)
                alert("白棋胜")

        })
    }
}