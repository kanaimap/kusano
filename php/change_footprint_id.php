<?php

$name = $_POST['name'];
$footprint_id = $_POST['footprint_id'];

if(isset($footprint_id))
{

	$dbHandle = mysql_connect('localhost','root','pwd');
	mysql_set_charset("utf8", $dbHandle);
	if($dbHandle == False)
	{
		print('can not connect dbn');
		exit;
	}
	
	$db = 'map';
	
	$sql = "UPDATE icon SET footprint_id = '$footprint_id' WHERE name = '$name'";
	
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