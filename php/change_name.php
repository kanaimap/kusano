<?php

$name_result = $_POST['name_result'];
$before_name = $_POST['before_name'];
$icon_id = $_POST['icon_id'];
$footprint_id = $_POST['footprint_id'];

if(isset($name_result))
{

	$dbHandle = mysql_connect('localhost','root','pwd');
	mysql_set_charset("utf8", $dbHandle);
	if($dbHandle == False)
	{
		print('can not connect dbn');
		exit;
	}
	
	$db = 'map';
	
	$sql = "UPDATE location SET name = '$name_result' WHERE name = '$before_name'";
	
	$rs = mysql_db_query($db,$sql);
	
	$sql = "UPDATE icon SET name = '$name_result' , icon_id = '$icon_id' , footprint_id = '$footprint_id' WHERE name  = '$before_name'";
	
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