<?php

if (empty($_POST['target']))
  { echo 'You haven\'t filled out all fields correctly. Please go back and try again.'; } 
else
{
$con=mysqli_connect("mysql6.000webhost.com","a7453331_admin","bronco909","a7453331_CS356");

 if (mysqli_connect_errno($con))
 {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
 }
 else
 {
  $course = $_POST['target'];
  $result = mysqli_query($con,"SELECT id,author,target,message,time FROM Post Where target='$course'");

  $Arr = array();
	while($data = mysqli_fetch_array($result))
	{
		$Arr[] = $data["author"]  $data["target"]  $data["message"]  $data["timeStamp"];
	}
  
  if (mysqli_error($con))
  { echo "Failed to connect to MySQL: " . mysqli_connect_error(); } 
 else
 {
	$i = 0;
    while($i < 4)
    { 
		//echo "Connected" . "\n";
		echo $Arr[$i] . "\n" ;
		$i++;
	}    
 }
  
 } 
 mysqli_close($con);
}
?>													