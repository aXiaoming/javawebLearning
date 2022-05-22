window.onload=function(){
	updateZJ();
	//当页面加载完成，我们需要绑定各种事件
	//根据id获取到表格
	var fruitTbl =  document.getElementById("tbl_fruit");
	//获取表格中的所有的行
	var rows = fruitTbl.rows ;
	for(var i = 1 ; i<rows.length-1 ; i++){
		var tr = rows[i];
		//1.绑定鼠标悬浮以及离开时设置背景颜色事件
		tr.onmouseover=showBGColor;
		tr.onmouseout=clearBGColor;
		//获取tr这一行的所有单元格
		var cells = tr.cells;
		var priceTD = cells[1];
		//2.绑定鼠标悬浮在单价单元格变手势的事件
		priceTD.onmouseover = showHand ;
		//3.绑定鼠标点击单价单元格的事件
		priceTD.onclick=editPrice;


		//7.绑定删除小图标的点击事件
		var img = cells[4].firstChild;
		if(img && img.tagName=="IMG"){
			//绑定单击事件
			img.onclick = delFruit ;
		}

	}
	
	// 添加与重置按钮
	var add_btn = document.getElementById("add_btn");
	var reset_btn = document.getElementById("reset_btn");
	
	add_btn.onclick = addRow;
	reset_btn.onclick = resetTable;
	
	
}

function addRow(){
	if(event && event.srcElement){
		var fname = document.getElementById("fname").value;
	
		
		var price = document.getElementById("price").value;
		
		
		var fcount = document.getElementById("fcount").value;

		var fruitTbl =  document.getElementById("tbl_fruit");
		
		var rows = fruitTbl.rows;
		// 返回一个tr
		var new_row = fruitTbl.insertRow(rows.length-1);
		fname_td = new_row.insertCell();
		fname_td.innerText = fname;
		
		price_td = new_row.insertCell();
		price_td.innerText = price;

		fcount_td = new_row.insertCell();
		fcount_td.innerText = fcount;
		
		xj_td = new_row.insertCell();
		
		cz_td = new_row.insertCell();
		cz_td.innerHTML = '<img src="imgs/del.jpg" class="delImg"/>'
		
		
	}
}

function resetTable(){
	if(event && event.srcElement ){
		// alert("点击了重置")
		
		// 必须要先获取到元素，然后才能进行操作。
		fname_input = document.getElementById("fname");
		fname_input.value = "";
		
		price_input = document.getElementById("price");
		price_input.value = '';
		
		fcount_input = document.getElementById("fcount");
		fcount_input.value = "";
		
	}
}


function delFruit(){
	if(event && event.srcElement && event.srcElement.tagName=="IMG"){
		//alert表示弹出一个对话框，只有确定按钮
		//confirm表示弹出一个对话框，有确定和取消按钮。当点击确定，返回true，否则返回false
		if(window.confirm("是否确认删除当前库存记录")){
			var img = event.srcElement ;
			var tr = img.parentElement.parentElement ;
			var fruitTbl = document.getElementById("tbl_fruit");
			fruitTbl.deleteRow(tr.rowIndex);

			updateZJ();
		}
	}

}

//当鼠标点击单价单元格时进行价格编辑
function editPrice(){
	if(event && event.srcElement && event.srcElement.tagName=="TD"){
		var priceTD = event.srcElement ;
		//目的是判断当前priceTD有子节点，而且第一个子节点是文本节点 ， TextNode对应的是3  ElementNode对应的是1
		if(priceTD.firstChild && priceTD.firstChild.nodeType==3){
			//innerText 表示设置或者获取当前节点的内部文本
			var oldPrice = priceTD.innerText ;
			//innerHTML 表示设置当前节点的内部HTML
			priceTD.innerHTML="<input type='text' size='4'/>";
			// <td><input type='text' size='4'/></td>
			var input = priceTD.firstChild;
			if(input.tagName=="INPUT"){
				input.value = oldPrice ;
				//选中输入框内部的文本
				input.select();
				//4.绑定输入框失去焦点事件 , 失去焦点，更新单价
				input.onblur=updatePrice ;

				//8.在输入框上绑定键盘摁下的事件，此处我需要保证用户输入的是数字
				input.onkeydown=ckInput;
			}
		}
		
	}
}

//检验键盘摁下的值的方法
function ckInput(){
	var kc = event.keyCode ;
	// 0 ~ 9 : 48~57
	//backspace : 8
	//enter : 13
	//console.log(kc);
	// 不是以上范围的按键，失去效果
	if(!( ( kc>=48 && kc<=57 ) || kc==8 || kc==13 )){
		event.returnValue=false;
	}
	// 回车键，相当于失去焦点
	if(kc==13){
		event.srcElement.blur();
	}

}

//更新单价的方法
function updatePrice(){
	if(event && event.srcElement && event.srcElement.tagName=="INPUT"){
		var input = event.srcElement ;
		var newPrice = input.value ;
		//input节点的父节点是td
		var priceTD = input.parentElement ;
		priceTD.innerText = newPrice ;

		//5. 更新当前行的小计这一个格子的值
		//priceTD.parentElement td的父元素是tr
		updateXJ(priceTD.parentElement);

	}
}

//更新指定行的小计
function updateXJ(tr){
	if(tr && tr.tagName=="TR"){
		var tds = tr.cells;
		var price = tds[1].innerText ;
		var count = tds[2].innerText ;
		//innerText获取到的值的类型是字符串类型，因此需要类型转换，才能进行数学运算
		var xj = parseInt(price) * parseInt(count);
		tds[3].innerText = xj ;

		//6. 更新总计
		updateZJ();
	}

}

//更新总计
function updateZJ(){
	var fruitTbl = document.getElementById("tbl_fruit");
	var rows = fruitTbl.rows ;
	var sum = 0 ;
	for(var i = 1; i<rows.length-1 ; i++){
		var tr = rows[i];
		var xj = parseInt(tr.cells[3].innerText);		//NaN    not a number 不是一个数字
		sum = sum + xj ;
	}
	rows[rows.length-1].cells[1].innerText = sum ;
}


//当鼠标悬浮时，显示背景颜色
function showBGColor(){
	//event : 当前发生的事件
	//event.srcElement : 事件源
	//alert(event.srcElement);
	//alert(event.srcElement.tagName);	--> TD
	if(event && event.srcElement && event.srcElement.tagName=="TD"){
		var td = event.srcElement ;
		//td.parentElement 表示获取td的父元素 -> TR
		var tr = td.parentElement ;
		//如果想要通过js代码设置某节点的样式，则需要加上 .style
		tr.style.backgroundColor = "navy" ;

		//tr.cells表示获取这个tr中的所有的单元格
		var tds = tr.cells;
		for(var i = 0 ; i<tds.length ; i++){
			tds[i].style.color="white";
		}
	}
}

//当鼠标离开时，恢复原始样式
function clearBGColor(){
	if(event && event.srcElement && event.srcElement.tagName=="TD"){
		var td = event.srcElement ;
		var tr = td.parentElement ;
		tr.style.backgroundColor="transparent";
		var tds = tr.cells;
		for(var i = 0 ; i<tds.length ; i++){
			tds[i].style.color="threeddarkshadow";
		}
	}
}

//当鼠标悬浮在单价单元格时，显示手势
function showHand(){
	if(event && event.srcElement && event.srcElement.tagName=="TD"){
		var td = event.srcElement ;
		//cursor : 光标
		td.style.cursor="hand";
	}

}