<HTML>
<meta http-equiv="Content-type" content="text/html; chentryet=UTF-8" />

<BODY>

<?php

$latitude = $_POST['lat'];
$longitude= $_POST['lon'];
$time = $_POST['time'];
$name = $_POST['name'];
$comment = $_POST['comment'];

if(isset($latitude))
{

	$dbHandle = mysql_connect('localhost','root','pwd');
	mysql_set_charset("utf8", $dbHandle);
	if ($dbHandle == False)
	{
		print ('can not connect dbn');	
		exit;
	}

	$db = 'map';

	$sql = "INSERT INTO location (name,latitude,longitude,time,comment)
             VALUES ('$name','$latitude','$longitude','$time','$comment')";

	$rs = mysql_db_query($db,$sql);
	if (!$rs)
	{
		print("SQL‚ÌŽÀs‚ÉŽ¸”s‚µ‚Ü‚µ‚½¡");
		exit;
	}

	mysql_free_result($rs);

	mysql_close($dbHandle);
}

?>
</BODY>
</HTML>