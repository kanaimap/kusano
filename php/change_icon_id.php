<?php

$icon_id = $_POST['ICON_ID'];
$name = $_POST['name'];
$footprint_id = $_POST['footprint_id'];

if(isset($icon_id))
{

	$dbHandle = mysql_connect('localhost','root','pwd');
	mysql_set_charset("utf8", $dbHandle);
	if($dbHandle == False)
	{
		print('can not connect dbn');
		exit;
	}
	
	$db = 'map';
	
	$sql = "UPDATE icon SET icon_id = '$icon_id' ,  footprint_id = '$footprint_id' WHERE name = '$name'";
	
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