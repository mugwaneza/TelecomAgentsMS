
// show student details
$(".detail").click(function() {
    var $row = $(this).closest("tr");    // Find the row
    var $rowId = $row.find(".id").text(); // Find the text


    var $district = $row.find(".district").text(); // Find the text
    var $sector = $row.find(".sector").text(); // Find the text
    var $cell = $row.find(".cell").text(); // Find the text
    var $firstname = $row.find(".firstname").text(); // Find the text
    var $lastname = $row.find(".lastname").text(); // Find the text
    var $phonenumber = $row.find(".phonenumber").text(); // Find the text
    var $village = $row.find(".agromeration").text(); // Find the text
    var $phone = $row.find(".phonenumber").text(); // Find the text
    var $address = $row.find(".address").text(); // Find the text
    var $nationality = $row.find(".nationality").text(); // Find the text
    var $nid = $row.find(".nid").text(); // Find the text
    var $company = $row.find(".company").text(); // Find the text
    var $sitename = $row.find(".sitename").text(); // Find the text
    var $passportphoto = $row.find(".passportphoto").text(); // Find the text
    var $airtimeno = $row.find(".airtimenumber").text(); // Find the text
    var $wallet = $row.find(".walletnumber").text(); // Find the text




    // Append variables to the model dialog
    $("#district").val("District :" + $.trim($district));
    $("#sector").val("Sector :" + $.trim($sector));
    $("#cell").val("Cell :" + $.trim($cell));
    $("#agromeration").val("Village :" + $.trim($village));
    $("#phonenumber").val("Phone number :" +$.trim($phone));
    $("#firstname").val("First name : " +$.trim( $firstname));
    $("#lastname").val("Last name : " + $.trim($lastname));
    $("#address").val("Address : " + $.trim($address));
    $("#nationality").val("Nationality : " + $.trim($nationality));
    $("#nid").val("National ID : " + $.trim($nid));
    $("#company").val("Company : " + $.trim($company));
    $("#sitename").val("Site name : " + $.trim($sitename));
    $("#walletno").val("Wallet number : " + $.trim($wallet));
    $("#airtimeno").val("Airtime number : " + $.trim($airtimeno));

     var photopath = $.trim($passportphoto);
     var path = photopath.replace("uploads\\", "uploads/")
    console.log(photopath.replace("uploads\\", "uploads/"));
     document.getElementById("passportphoto").setAttribute("src", "../assets/"+path );
});

  // When accept button is clicked
$(".accept").click(function() {

    $("#approve").modal('show');

    var $row = $(this).closest("tr");    // Find the row
    var $rowId = $row.find(".id").text(); // Find the text
    var userid = $("#userid").val($.trim($rowId));

    // var data = $("#approvalform").serialize();

});


// When accept button is clicked
$(".reject").click(function() {

    var $row = $(this).closest("tr");    // Find the row
    var $rowId = $row.find(".id").text(); // Find the text
    var rejectid = $("#rejectid").val($.trim($rowId));

    $("#rejectmodel").modal('show');

});


    // When edit button is clicked
    $(".editbtn").click(function() {

        var $row = $(this).closest("tr");    // Find the row
        var $rowId = $row.find(".id").text(); // Find the text
        var $fullname = $row.find(".fullname").text(); // Find the text
        var $gender = $row.find(".gender").text(); // Find the text
        var $company = $row.find(".company").text(); // Find the text
        var $email = $row.find(".email").text(); // Find the text
        var $address = $row.find(".address").text(); // Find the text

         $("#userid").val($.trim($rowId));
         $("#fullname").val($.trim($fullname));
         $("#gender").val($.trim($gender));
         $("#company").val($.trim($company));
         $("#email").val($.trim($email));
         $("#address").val($.trim($address));
        $("#editmodel").modal('show');

    });



// When delete button is clicked
$(".deletebtn").click(function() {

    var $row = $(this).closest("tr");    // Find the row
    var $rowId = $row.find(".id").text(); // Find the text

    $("#deletid").val($.trim($rowId));
    $("#deletemodel").modal('show');

});


     $(".mylink").click(function () {
         var data = $("#link").val();
         localStorage.setItem("chatid",data )
     });


     $(".btnsub").click(function () {
       var chatid = localStorage.getItem("chatid")
         $("#chatid").val(chatid);

     });


      // when district option is selected get its sectors
     function changeFunc(){
       var selectBox = document.getElementById("distri");
       var district_id = selectBox.options[selectBox.selectedIndex].value;

         $('#sector').empty();
         $('#cell').empty();

         $.ajax(
            {
             type: "GET",
             url:"/agent/sectors/"+district_id,
             data: district_id,
             success:function(data) {

              $('#sector').append($('<option></option>').val("").html(" -- select sector -- "));
                 data.forEach(function (v) {

                       // Append all fetched sectors to sector's dropdown
                     $('#sector').append($('<option></option>').val(v.id).html(v.sector));
                 });
                 // $("#div").html(data.passportphoto);
                 //     $('#result').html(data);
             }
         });
    }


   // when sector option is selected get its cells
   function changeFunc2(){
    var selectBox = document.getElementById("sector");
    var cellid = selectBox.options[selectBox.selectedIndex].value;

    $('#cell').empty();
    $.ajax(
        {
            type: "GET",
            url:"/agent/cells/"+cellid,
            data: cellid,
            success:function(data) {

       $('#cell').append($('<option></option>').val("").html(" -- select cell -- "));
       // Append all fetched sectors to cell's dropdown
                data.forEach(function (v) {
                    $('#cell').append($('<option></option>').val(v.id).html(v.cell));
                });

            }
        });
        }


            $(document).ready(function() {
                $('#searched').hide();
                $('#searched').show();
            });


       // when searchbox key pressed

        var textbox = document.getElementById("search");
        textbox.addEventListener("keypress", function onEvent(event) {
        if (event.key === "Enter") {

            var mobile = document.getElementById("search").value;
            // show hidden table
            // hide the table of approved

            window.location.href = "/manage/search/"+mobile;

        }
            $('#searched').show();
            document.getElementById('approvedusers').style.display = 'none';

        });







