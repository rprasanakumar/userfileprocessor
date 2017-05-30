<!DOCTYPE HTML>
<html>
	<head>
		<title>User File Processor</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="resources/css/main.css" />
  
    
    <title>User File Processor
    </title>
	<script>
	
	
	
	function onLoad() {
		getLocation();
		 document.getElementById("urlformtext").value = "{\"firstName\":\"Donald\",\"lastName\":\"Duck\",\"address\":\"12 street\",\"zipCode\":\"99999\",\"phoneNumber\":\"(890)-890-9090\",\"color\":\"Red\"}";
	}
	
	function getColor(){		
	
		var urlColor = "webapi/user/color";
		var client = new XMLHttpRequest();
		client.open("GET", urlColor, false);

		client.setRequestHeader("Content-Type", "application/json");

		client.send();

		if (client.status == 200){
			document.getElementById("response").value = client.responseText;
			
		}
		    
		   
	}
	
	
	function getColorNname(){
		
		var urlColor = "webapi/user/name";
		var client = new XMLHttpRequest();

		client.open("GET", urlColor, false);

		client.setRequestHeader("Content-Type", "application/json");

		client.send();

		if (client.status == 200){
			document.getElementById("response").value = client.responseText;
			
		}
		    
		   
	}
	
	
	function getVenues(){
		var query = document.getElementById("query").value;
		if(query==""){
			query ="all"
		}
		
		var client = new XMLHttpRequest();
		var lat =document.getElementById("lat").value;
		if(lat==null || lat=="" ){
			lat = "35.3280913,-80.8172195";
		}
		var urlVenue = "webapi/user/venue/"+query+"/"+lat;
		client.open("GET", urlVenue, false);
		client.setRequestHeader("Content-Type", "application/json");

		client.send();

		if (client.status == 200){
			document.getElementById("response").value = client.responseText;
			
		}
		    
		   
	}
	
	function urlPost(){
		var url = "webapi/user/sendrecord";
		var urlformtext = document.getElementById("urlformtext").value;
		var client = new XMLHttpRequest();
		if(urlformtext==null || urlformtext==""){
			
			alert("JSON cannot be empty");
			return;
		}
		

		client.open("POST", url, false);

		client.setRequestHeader("Content-Type", "application/json");

		client.send(urlformtext);

		if (client.status == 200){
			document.getElementById("response").value = client.responseText;
			
		}
		    
		   
	}
	



	function getLocation() {
	    if (navigator.geolocation) {
	        navigator.geolocation.getCurrentPosition(showPosition);
	    }
	}

	function showPosition(position) {
		 document.getElementById("lat").value = position.coords.latitude +"," + position.coords.longitude;
	}
	
	
	function getLog(){
			
		var client = new XMLHttpRequest();
		var urlVenue = "webapi/user/getlogfile/";
		client.open("GET", urlVenue, false);
		client.setRequestHeader("Content-Type", "application/json");

		client.send();

		if (client.status == 200){
			document.getElementById("response").value = client.responseText;
			
		}
		    
		   
	}
	

</script>
  
   <style>
  	.demo1 { 
  	font-size: 100px;
  	font-family: "Century Gothic", "Helvetica", sans-serif;
    font-weight: bold;
    font-weight: italic;
    text-align: center;
    color: #333;
    background-color: #666;
    text-shadow: 0px 1px 0px rgba(255,255,255,.5); /* 50% white from bottom */
}
   </style>
    </head>
<body onload="onLoad()">
		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Header -->
					<header id="header">
				
  <div class="container">
    <div class="demo1">
          <a href="#"> User File Processor</a>
    </div>   
</div>
		</header>


				<!-- Main -->
<section id="main">

<br>

<!-- <input placeholder="Response" type="textarea" id="response" name="response" readonly/> -->
<form name="groupUpload" method ="post" action="javascript:void(0);" enctype="multipart/form-data" >
<span>
Response Pan for all your request
<textarea id="response" cols="10" readonly> 
</textarea>
</span>
	<br><br>

     	 <button  type="submit" class="yj-btn" onclick="getColor()" ><span>Get Color Count</span></button>
		 <button  type="submit" class="yj-btn" onclick="getColorNname()" ><span>Get Color Count Name</span></button>
		 
		 </form>
		<div id="wrapper">
			<font color="red">${displayContent}</font>
			<br><H4>Select a file to Upload</H4>
			
			
			
		<form name="groupUpload" method ="post" action="webapi/user/file/" enctype="multipart/form-data" >
		
		
	
		    <input type='file' name='file'><br><br>
		    <input type='submit' name='upload_btn' value='upload' >
		</form>
		
			<font color="red">${displayContent}</font>
			<br><H4>Input a single row in JSON format</H4>(edit any record below)
		
		 <form name="urlform"  accept-charset="UTF-8" action="javascript:void(0);" method="post">
		 
		 <br>
		 
   			 <span> <textarea id="urlformtext" cols="100" > 
			</textarea></span>
 

        	  <br><br>

       		 <button  type="submit" class="yj-btn"  onclick="urlPost()" ><span>Send</span></button>
		</form>
		</div>

		
		
			


<br>

  <form accept-charset="UTF-8" action="javascript:void(0);" method="post">
      

  
		 <br><br>
		 <span>  <input placeholder="Search Query for find venues" aria-required="true" id="query" name="query" size="30" type="text" /></span><br>
        <button  type="submit" class="yj-btn" onclick="getVenues()" ><span>Get Venues</span></button>

  </form>
  
    <form accept-charset="UTF-8" action="javascript:void(0);" method="post">
      

  
		 <br><br>
        <button  type="submit" class="yj-btn" onclick="getLog()" ><span>Get Log</span></button>

  </form>
  
  
  
  
  Your Location:

<input type="text" id="lat" readonly>

</section>


</div>




	</body>
</html>