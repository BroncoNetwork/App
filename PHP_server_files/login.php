<?php

if (empty($_POST['username']) || empty($_POST['password']))
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
  $username = $_POST['username'];
  $password = $_POST['password'];
  $result = mysqli_query($con,"SELECT username,email,course1,course2,course3,course4,course5,course6,course7 FROM User Where username='$username' and password='$password'");

	while($data = mysqli_fetch_array($result))
	{
		$Username = $data["username"];
		$Email = $data["email"];
		$Course1 = $data["course1"];
		$Course2 = $data["course2"];
		$Course3 = $data["course3"];
		$Course4 = $data["course4"];
		$Course5 = $data["course5"];
		$Course6 = $data["course6"];
		$Course7 = $data["course7"];
	}
  
  if (mysqli_error($con))
  { echo "Failed to connect to MySQL: " . mysqli_connect_error(); } 
 else
 {
    if($Username != "")
    { 
		echo "Connected" . "\n";
		echo $Username . "\n" ;
		echo $Email . "\n" ;
		echo $Course1 . "\n" ;
		echo $Course2 . "\n" ;
		echo $Course3 . "\n" ;
		echo $Course4 . "\n" ;
		echo $Course5 . "\n" ;
		echo $Course6 . "\n" ;
		echo $Course7 . "\n" ;
	}    
 }
  
 } 
 mysqli_close($con);
}
?>													