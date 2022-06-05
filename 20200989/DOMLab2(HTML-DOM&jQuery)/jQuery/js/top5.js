$(document).ready(function(){
  $("#cds").children("img").click(addToTop5);   
  	// 또는 $("#cds > img").click(addToTop5);
  
  $(":button").click(startOver);
});

function addOnClickHandlers() {
/*
  var cdsDiv = document.getElementById("cds");
  var cdImages = cdsDiv.getElementsByTagName("img");
  for (var i=0; i<cdImages.length; i++) {
    cdImages[i].onclick = addToTop5;
  }
*/
  $("#cds>img").click(addToTop5);
}

function addToTop5() {
  var imgElement = this;
/*
  var top5Element = document.getElementById("top5");
  var numCDs = 0;
  for (var i=0; i<top5Element.childNodes.length; i++) {
    if (top5Element.childNodes[i].nodeName.toLowerCase() == "img") {
      numCDs = numCDs + 1;
    }
  }
*/  
  var numCDs = $("#top5").children("img").length;
  	// 또는 var numCDs = $("#top5 > img").length;

  if (numCDs >= 5) {
    alert("You already have 5 CDs. Click \"Start Over\" to try again.");
    return;
  }
/*
  top5Element.appendChild(imgElement);
  imgElement.onclick = null;
  
  var newSpanElement = document.createElement("span");
  newSpanElement.className = "rank";
  var newText = document.createTextNode(numCDs + 1);
  newSpanElement.appendChild(newText);
  top5Element.insertBefore(newSpanElement, imgElement);
*/
  $("#top5").append(imgElement);
  $(imgElement).off("click");   
//  $(imgElement).click(moveToOriginalPosition); // 선택된 이미지를 제자리로 복귀(이동)시킴
  
  var newSpanElement = document.createElement("span");
  $(newSpanElement).addClass("rank");
  $(newSpanElement).text(numCDs + 1);
  $(newSpanElement).insertBefore(imgElement);  
    // 또는 $(imgElement).before(newSpanElement);  
  
  // 또는 아래와 같이 구현 가능
/*
  var newSpanElement = "<span></span>";
  $(newSpanElement).addClass("rank")
  				.text(numCDs + 1)
  				.insertBefore(imgElement);
*/
}

function startOver() {
/*  
  var top5Element = document.getElementById("top5");
  var cdsElement = document.getElementById("cds");
  while (top5Element.hasChildNodes()) {
    var firstChild = top5Element.firstChild;
    if (firstChild.nodeName.toLowerCase() == "img") {
      cdsElement.appendChild(firstChild);
    } else {
      top5Element.removeChild(firstChild);
    }
  }
  addOnClickHandlers();
*/
  $("#top5").children("img").each( 
  	// 또는  $("#top5 > img").each( 
	function() {
	  $(this).appendTo("#cds");
	  $(this).off("click");
	  $(this).on("click", addToTop5);
	}
  );  
  $("#top5").children().remove();  
  
  // 또는 다음과 같이 구현 가능
/*
  $("#top5>img").appendTo("#cds")	// 위치 이동 
				.off("click")		// click 이벤트 핸들러 제거
				.on("click", addToTop5);  // click 이벤트 핸들러 추가
  $("#top5").children().remove();	// 모든 <span> 삭제  
*/
}

function moveToOriginalPosition() { // 선택된 top5 아래의 하나의 이미지를 제자리로 이동시킴
	var imgElement = this; 		// 선택된 <img> 엘리먼트

	// imgElement의 순위를 나타내는 <span>을 삭제
	
	// cds의 <img>들 중 id 값이 imgElement의 id 값보다 크면서 가장 가까운 것을 찾아
	// imgElement를 cds의 제 위치에 삽입 
	
	// cds의 <img>들 중 id 값이 imgElement보다 큰 것이 없을 경우 위에서 처리되지 않으므로 별도로 처리
	
	// top5 아래의 imgElement 다음에 있었던 이미지들의 순위 값을 변경
	
	// imgElement에 대해 onclick 이벤트 핸들러 재설정
			
}
