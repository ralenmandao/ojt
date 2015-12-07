var cells = document.getElementsByTagName("td");
var gridTable = document.getElementById("gridTable");
var isMove = false;
var canMove = true;
var selectedColor = "blue";
var t;
var canMove = true;
var notAble = false;
var fontColor = "white";

function randomizeTableNumber() {
    fontColor = "white"
    for (var i = 0; i < 3; i++) {
        gridTable.innerHTML += "<tr><td></td><td></td><td></td></tr>"
    }
    var numbers = new Array();
    var cp = document.getElementsByTagName("td");
    for (var i = 0; i < cp.length; i++) {
        numbers.push(cp[i]);
    }

    var indexToRemove = Math.floor(Math.random() * numbers.length)
    empty = numbers[indexToRemove];
    empty.style.background = "white"
    numbers.splice(indexToRemove, 1);

    for (var i = 0; i < cp.length - 1; i++) {
        var indexToRemove = Math.floor(Math.random() * numbers.length)
        var edit = numbers[indexToRemove];
        edit.innerHTML = i + 1;
        edit.style.color = fontColor
        numbers.splice(indexToRemove, 1);
    }
}

function randomizeTableImage() {
    fontColor = "transparent"
    for (var i = 0; i < 3; i++) {
        gridTable.innerHTML += "<tr><td></td><td></td><td></td></tr>"
    }
    var numbers = new Array();
    var cp = document.getElementsByTagName("td");
    for (var i = 0; i < cp.length; i++) {
        numbers.push(cp[i]);
    }

    var indexToRemove = Math.floor(Math.random() * numbers.length)
    empty = numbers[indexToRemove];
    empty.style.background = "white"
    numbers.splice(indexToRemove, 1);

    for (var i = 0; i < cp.length - 1; i++) {
        var indexToRemove = Math.floor(Math.random() * numbers.length)
        var edit = numbers[indexToRemove];
        edit.innerHTML = i + 1;
        edit.style.background = "url(img/" + (i + 1) + ".jpg"
        edit.style.backgroundSize = "100% 100%"
        edit.style.color = fontColor
        numbers.splice(indexToRemove, 1);
    }
}

function randomizeTableNumberLarge() {
    fontColor = "white"
    for (var i = 0; i < 4; i++) {
        gridTable.innerHTML += "<tr><td></td><td></td><td></td><td></td></tr>"
    }
    var numbers = new Array();
    var cp = document.getElementsByTagName("td");
    for (var i = 0; i < cp.length; i++) {
        numbers.push(cp[i]);
    }

    var indexToRemove = Math.floor(Math.random() * numbers.length)
    empty = numbers[indexToRemove];
    empty.style.background = "white"
    numbers.splice(indexToRemove, 1);

    for (var i = 0; i < cp.length - 1; i++) {
        var indexToRemove = Math.floor(Math.random() * numbers.length)
        var edit = numbers[indexToRemove];
        edit.innerHTML = i + 1;
        edit.style.color = fontColor
        numbers.splice(indexToRemove, 1);
    }


}

function move(target, destination) {
    target.style["position"] = "relative";
    target.style["left"] = "0px";
    target.style["top"] = "0px";
    target.style["zIndex"] = "99999";
    moveCell(target, destination);
    //console.log(isMove);
    if (isMove) {
        setTimeout(function() {
            var prevBg = target.style["background"]
            target.style["background"] = "white";
            destination.innerHTML = target.innerHTML;
            target.innerHTML = "";
            destination.style["background"] = prevBg;
            destination.style.color = fontColor
            destination.style.backgroundSize = "100% 100%"
            target.style["left"] = "0px";
            target.style["top"] = "0px";
            target.style["zIndex"] = "0";
            empty = target;
            console.log("enabling moving");
            setTimeout(function() {
                canMove = true;
                if (checkWin()) {
                    clearTimeout(t)
                    alert("You win total time : " + h1.innerHTML)
                }
            }, 100);
        }, 100);
    }

    if (notAble) {
        notAble = false;
        setTimeout(function() {
            canMove = true;
        }, 100);
    }
}

function moveCell(target, destination) {
    isMove = false;
    var tCol = parseInt(target.cellIndex);
    var dCol = parseInt(destination.cellIndex);
    var tRow = parseInt(target.parentNode.rowIndex);
    var dRow = parseInt(destination.parentNode.rowIndex);
    if ((tCol - dCol) == 1 && dRow == tRow) {
        target.style["left"] = (parseInt(target.style["left"]) - 11) + "px";
        if (parseInt(target.style["left"]) != -55) {
            setTimeout(function() {
                moveCell(target, destination);
            }, 20);
        }
        isMove = true;
    } else if ((tCol - dCol) == -1 && dRow == tRow) {
        target.style["left"] = (parseInt(target.style["left"]) + 11) + "px";
        if (parseInt(target.style["left"]) != 55) {
            setTimeout(function() {
                moveCell(target, destination);
            }, 20);
        }
        isMove = true;
    } else if (tCol == dCol && (tRow - dRow) == 1) {
        target.style["top"] = (parseInt(target.style["top"]) - 11) + "px";
        if (parseInt(target.style["top"]) != -55) {
            setTimeout(function() {
                moveCell(target, destination);
            }, 20);
        }
        isMove = true;
    } else if (tCol == dCol && (tRow - dRow) == -1) {
        target.style["top"] = (parseInt(target.style["top"]) + 11) + "px";
        if (parseInt(target.style["top"]) != 55) {
            setTimeout(function() {
                moveCell(target, destination);
            }, 20);
        }
        isMove = true;
    } else {
        notAble = true;
    }
}

function checkWin() {
    for (var i = 0; i < cells.length - 1; i++) {
        console.log(cells[i].innerHTML + " " + (i + 1))
        if (cells[i].innerHTML != (i + 1)) {
            return false;
        }
    }
    return true;
}

var h1 = document.getElementsByTagName('h1')[0],
    start = document.getElementById('start'),
    seconds = 0,
    minutes = 0,
    hours = 0;

function add() {
    seconds++;
    if (seconds >= 60) {
        seconds = 0;
        minutes++;
        if (minutes >= 60) {
            minutes = 0;
            hours++;
        }
    }

    h1.textContent = (hours ? (hours > 9 ? hours : "0" + hours) : "00") + ":" + (minutes ? (minutes > 9 ? minutes : "0" + minutes) : "00") + ":" + (seconds > 9 ? seconds : "0" + seconds);

    timer();
}

function timer() {
    t = setTimeout(add, 1000);
}

start.onclick = function() {
    clearTimeout(t);
    gridTable.innerHTML = ""
    h1.innerHTML = "00:00:00"
    seconds = 0
    minutes = 0
    hours = 0;
    var mode = document.getElementById("mode");
    if (mode.value == "1") {
        randomizeTableNumber();
    } else if (mode.value == "2") {
        randomizeTableNumberLarge();
    } else {
        randomizeTableImage()
    }
    cells = document.getElementsByTagName("td");
    for (var i = 0; i < cells.length; i++) {
        cells[i].onclick = function() {
            var col = this.cellIndex;
            var row = this.parentNode.rowIndex;
            var cell = gridTable.rows[row].cells[col];

            if (canMove && this.innerHTML != "") {
                console.log("disabling moving");
                canMove = false;
                console.log("moving");
                move(this, empty);
                console.log("finsih moving");
            }
        }
    }

    gridTable.style.display = "block";
    timer()
}