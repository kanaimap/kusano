<HTML>
<meta http-equiv="Content-type" content="text/html; chentryet=UTF-8" />

<BODY>

<?php

$latitude = $_POST['lat'];
$longitude= $_POST['lon'];
$time = $_POST['time'];
$name = $_POST['name'];

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

	$sql = "INSERT INTO location (name,latitude,longitude,time)
             VALUES ('$name','$latitude','$longitude','$time')";

	$rs = mysql_db_query($db,$sql);
	if (!$rs)
	{
		print("SQLの実行に失敗しました｡");
		exit;
	}

	mysql_free_result($rs);

	mysql_close($dbHandle);
}

?>
</BODY>
</HTML>