<?php

if (empty($_POST['course']) || empty($_POST['username']) || empty($_POST['courseNum']))
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
  $course = $_POST['course'];
  $username = $_POST['username'];
  $courseNum = $_POST['courseNum'];
  switch ($courseNum) {
	case "Course1":
		$result = mysqli_query($con,"UPDATE User SET Course1 = '$course' WHERE username = '$username'");
		break;
	case "Course2" :
		$result = mysqli_query($con,"UPDATE User SET Course2 = '$course' WHERE username = '$username'");
		break;
	case "Course3" :
		$result = mysqli_query($con,"UPDATE User SET Course3 = '$course' WHERE username = '$username'");
		break;
	case "Course4" :
		$result = mysqli_query($con,"UPDATE User SET Course4 = '$course' WHERE username = '$username'");
		break;
	case "Course5" :
		$result = mysqli_query($con,"UPDATE User SET Course5 = '$course' WHERE username = '$username'");
		break;
	case "Course6" :
		$result = mysqli_query($con,"UPDATE User SET Course6 = '$course' WHERE username = '$username'");
		break;
	case "Course7" :
		$result = mysqli_query($con,"UPDATE User SET Course7 = '$course' WHERE username = '$username'");
		break;
	default :
		echo "Failed to add";
	}
  
  if (mysqli_error($con))
  { echo "Failed to connect to MySQL: " . mysqli_connect_error(); } 
 else
 { 
 }
  
 } 
 mysqli_close($con);
}
?>			