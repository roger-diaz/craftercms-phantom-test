<!DOCTYPE html>
<html>
	<head>
		<title>Test for upload</title>
		
		<link href="/tests/yui/assets/skins/default/skin.css" type="text/css" rel="stylesheet">
   <script src="yui/utilities/utilities.js" type="text/javascript"></script>
   <script src="yui/button/button-min.js" type="text/javascript"></script>
   <script src="yui/container/container-min.js" type="text/javascript"></script>
   <script src="yui/menu/menu-min.js" type="text/javascript"></script>
   <script src="yui/json/json-min.js" type="text/javascript"></script>
   <script src="yui/selector/selector-min.js" type="text/javascript"></script> 
   <script src="yui/connection/connection-min.js" type="text/javascript"></script>
   <script src="yui/element/element-min.js" type="text/javascript"></script>
   <script src="yui/dragdrop/dragdrop-min.js" type="text/javascript"></script>
   <script src="yui/yahoo-dom-event/yahoo-dom-event.js" type="text/javascript"></script>
   <script src="yui/animation/animation-min.js" type="text/javascript"></script>
   <script src="yui/resize/resize-min.js" type="text/javascript"></script>
   <style type="text/css">

#message {
   background-color: lightblue;
}

input[type='button'] {
    background-color: #C0C0C0;
    border: 1px solid #002185;
    border-radius: 3px 3px 3px 3px;
    color: #000000;
    font: 12px Arial,Helvetica;
    min-width: 6em;
    padding: 5px 10px;
}

input[type='button']:hover {
 	background-color: #6C88A2;
    color: #FFFFFF;
    cursor: pointer;
}

.contentTypePopupInner {
    background-color: #D6D6D6;
    border: 5px solid #000000;
}

.contentTypePopupHeader {
    font: 1.8em arial;
    margin: 0;
    padding: 6px 0;
}

.contentTypePopupBtn {
    margin: 15px 0 0;
    padding: 0;
    text-align: center;
}
.yui-panel {
left: 0;
    position: relative;
    top: 0;
    z-index: 1;
    }
   </style>
   
	</head>
	<body>
		<h1>Upload Sample</h1>
		<div id="message" class="message"></div>
		<br />
		<input type="button" id="openPopup" value="upload"/>
		
		
		
		
		
		<script src="resources/js/upload.js" type="text/javascript"></script>
	</body>
</html>