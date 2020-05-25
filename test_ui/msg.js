var extId = "knldjmfmopnpolahpmmgbagdohdnhkik";

function sendExtMessageCert() {
    chrome.runtime.sendMessage(extId, {type: "cert"},
      function(response) {
        console.log(response);
        if (!response.success)
          console.log("worked");

        if (response.success)
          alert('ok');

        document.getElementById('response').innerHTML += "<p>" + JSON.stringify(response) + "</p>";
      });
}

function addOption(valArr){
  var objSel = document.getElementById("selbox");
  for (i=0; i < valArr.length; i++){
    var objOption = document.createElement("option");       
    objOption.text = valArr[i] + "개";
    objOption.value = valArr[i];
   
    objSel.options.add(objOption);
  }
}

function removeOption(){
  var objSel = document.getElementById("selbox");

  //alert(objSel.options[i].text +"//"+ objSel.options[i].value +"//"+ i);
  for(i=objSel.length; i>=0; i--){
    objSel.options[i]=null;
  }

}

function sendExtMessageSign() {
    chrome.runtime.sendMessage(extId, {type: "sign", content: "<signedingoajdkasd>", thumbprint: "c914cafe6b7ec2d01a0c709ec4e7a4afa0081e67"},
      function(response) {
        
        console.log(response);
        removeOption();
		
		var result = '';
		var list = response.certificates;
        for(idx in list){
			result += list[idx] + '</br>';
		};
		addOption(list);

        if (!response.success)
          console.log("did not work");

        if (response.success)
          alert('ok');

        document.getElementById('response').innerHTML += "<p>" + result + "</p>";
      });
}

document.addEventListener('DOMContentLoaded', function () {
  document.getElementById('sign-button').addEventListener(
      'click', sendExtMessageSign);
  document.getElementById('cert-button').addEventListener(
      'click', sendExtMessageCert);
});
