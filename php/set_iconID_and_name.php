<?php

$name = $_POST['name'];
$icon_id = $_POST['ICON_ID'];
$footprint_id = $_POST['footprint_id'];

if(isset($name))
{

	$dbHandle = mysql_connect('localhost','root','pwd');
	mysql_set_charset("utf8", $dbHandle);
	if($dbHandle == False)
	{
		print('can not connect dbn');
		exit;
	}
	
	$db = 'map';
	
		
	$sql = "INSERT INTO icon (name,icon_id,footprint_id)
		VALUES ('$name','$icon_id','$footprint_id')";
	
	$rs = mysql_db_query($db,$sql);
		



	mysql_free_result($rs);

	mysql_close($dbHandle);
}

?>