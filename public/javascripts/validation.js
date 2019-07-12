
// Register cell form

$('#cellform').validate({
    rules : {
        sector : {
            required : true,
        },
        cell :{
            required : true,
         },

       description :{
            required : true,
         },

    }
});


// Register Sector
$('#sectorform').validate({
    rules : {
        district : {
            required : true,
        },
        stector :{
            required : true,
         },

       description :{
            required : true,
         },

    }
});




// Register district

$('#districtform').validate({
    rules : {
        province : {
            required : true,
        },
        district :{
            required : true,
         },

       description :{
            required : true,
         },

    }
});


// Register district

$('#provinceform').validate({
    rules : {
        province : {
            required : true,
        },
        description :{
            required : true,
        },

    }
});



// Agent signup form
$('#agentsigupform').validate({
     rules : {
         names : {
            required : true,
             minlength : 6
         },
         gender : {
            required : true
        } ,
         email : {
            required : true,
             email: true
        },
        password : {
            required : true,
            minlength : 8        }


    }
});


// Agent signup form
$('#adminsignup').validate({
    rules : {
        fullname : {
            required : true,
            minlength : 6
        },
        gender : {
            required : true
        } ,
        email : {
            required : true,
            email: true
        },
        password : {
            required : true,
            minlength : 8
         },
        company : {
            required : true,
         },
        gender : {
            required : true,
         },
        address : {
            required : true,
         }


    }
});


$('#agentsigninform').validate({

    rules : {
        email : {
            required : true,
            email : true
        },
        password : {
            required : true,
        }

    }
});


$('#applicationForm').validate({

    rules : {
        district : {
            required : true,
        },
        sector : {
            required : true,
        },
        cell : {
            required : true,
        },
        agglomeration : {
            required : true,
        },
        firstname : {
            required : true,
        },
        lastname : {
            required : true,
        },
        phone : {
            required : true,
            number:true,
            minlength:10
        },

        nationality : {
            required : true,
        },
        address : {
            required : true,
        },
        nationalid : {
            required : true,
            number:true,
            minlength:18,
            maxlength:18
        },
        company : {
            required : true,
        },
        sitename : {
            required : true,
        },
        passport : {
            required : true,
            accept: "jpeg|pjpeg"
           },


    }
});





