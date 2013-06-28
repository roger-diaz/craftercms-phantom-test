var YDom = YAHOO.util.Dom;
var YEvent = YAHOO.util.Event;

var eventParams = {};
var dialog = null;

function createDialog(){
	var popupId = "upload-popup-div"
	YDom.removeClass(popupId, "yui-pe-content");
	
	var newdiv = YDom.get(popupId);
	if (newdiv == undefined) {
		newdiv = document.createElement("div");
		document.body.appendChild(newdiv);
	}
	
	newdiv.setAttribute("id",popupId);
	newdiv.className= "yui-pe-content";
    newdiv.innerHTML = '<div class="contentTypePopupInner" id="upload-popup-inner">' +
                       '<div class="contentTypePopupContent" id="contentTypePopupContent"> ' +
                       '<div class="contentTypePopupHeader">Upload</div> ' +
                       '<div>Please select a file to upload</div> ' +
					   '<div><form id="asset_upload_form">' +
					   '<div><table><tr><td><input type="hidden" name="site" value="' + 'mysite' + '"/></td>' +
					   '<td><input type="hidden" name="path" value="' + '/' + '"/></td></tr>' +
					   '<tr><td>File:</td><td><input type="file" name="file" id="uploadFileNameId"/></td></tr>' +
					   '</table></div>' +
					   '<div class="contentTypePopupBtn"> ' +
					        '<input type="button" class="popup-button" id="uploadButton" value="Upload" />' +
                            '<input type="button" class="popup-button" id="uploadCancelButton" value="Cancel" /></div>' +
					   '</form></div>' +
					   '<div><div  style="visibility:hidden; margin-bottom:1.5em;" id="indicator">Uploading...</div>' + 
                       '</div> ' +
                       '</div>';
	
	document.getElementById("upload-popup-inner").style.width = "350px";
	document.getElementById("upload-popup-inner").style.height = "165px";

	 // Instantiate the Dialog
	upload_dialog = new YAHOO.widget.Dialog("upload-popup-div", 
							{ width : "360px",
							  height : "175px",	
							  fixedcenter : true,
							  visible : false,
							  modal:true,
							  close:false,
							  constraintoviewport : true,
							  underlay:"none"							  							
							});	
							
	// Render the Dialog
	upload_dialog.render();
	
	var eventParams = {
		self: this
	};
	YAHOO.util.Event.addListener("uploadButton", "click", uploadPopupSubmit, eventParams);
	YAHOO.util.Event.addListener("uploadCancelButton", "click", function() {
		dialog.destroy();
	});

	
	return upload_dialog;
}

function openPopup(){
	dialog = createDialog();
	dialog.show();
};

function uploadPopupSubmit(){
	var callback = {
		success: function(data){
			var callback2 = {
					success: function(data) {
						var d = eval("(" + data.responseText + ")");
						if(d.exists){
							uploadFile();
						}
					},
					failure: function() {
						
					}
			};
			
			YAHOO.util.Connect.asyncRequest('GET', "/phantom-tests/checkFile", callback2);
		},
		failure: function(err){
			
		}
	};
	
	YAHOO.util.Connect.asyncRequest('GET', "/phantom-tests/checkFile", callback);
}

function uploadFile() {
	var uploadHandler = {
			upload: function(o) {
				console.log(o.responseText);
				var r = eval('(' + o.responseText + ')');
				if(r.hasError){
					var errorString = '';
					for(var i=0; i < r.errors.length; i++){
						errorString += r.errors[i];
					}
					alert(errorString);
				}else{
					dialog.destroy();		
					YDom.get("message").innerHTML = "<span id='msg'>" + r.result + " " + r.path + "</span>";
				}
			}
		};
		YAHOO.util.Connect.setForm('asset_upload_form', true);
		YAHOO.util.Connect.asyncRequest('POST', "/phantom-tests/upload", uploadHandler);
}
	
function init() {
	YEvent.addListener("openPopup", "click", openPopup, null);
}

init();