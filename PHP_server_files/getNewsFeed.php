<?php
 
$con=mysqli_connect("mysql6.000webhost.com","a7453331_admin","bronco909","a7453331_CS356");
 
 if (mysqli_connect_errno($con))
 {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
 }
 else
 {
  $course1 = $_POST['target1'];
  $course2 = $_POST['target2'];
  $course3 = $_POST['target3'];
  $course4 = $_POST['target4'];
  $course5 = $_POST['target5'];
  $course6 = $_POST['target6'];
  $course7 = $_POST['target7'];
  $result = mysqli_query($con,"SELECT author,target,message,timeStamp FROM Post Where target='$course1' or target='$course2' or target='$course3' or target='$course4' or target='$course5' or target='$course6' or target='$course7'");
 
  while($row = mysqli_fetch_array($result))
  {
     echo $row[0]."<a>".$row[1]."<a>".$row[2]."<a>".$row[3]."\n";
  }
 } 
 mysqli_close($con);

?>