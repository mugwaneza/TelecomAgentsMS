
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
            minlength:10,
            maxlength:10
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
            minlength:16,
            maxlength:16
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


$('#agentchat').validate({

    rules : {
        message : {
            required : true,
        }

    }
});



// update decision of user - validation
$('#myupdate').validate({
    rules : {
        distri2 : {
            required : true,
        },
        sector2 : {
            required : true
        } ,
        cell2 : {
            required : true,
        },
        agglomeration2 : {
            required : true,
                 } ,
        passport2 : {
            required : true,
                 }  ,
        nationality2 : {
            required : true,
                 }
        ,
        nationalid2 : {
            required : true,
            minlength :16,
            maxlength:16
        }
        ,
        walletnumber2 : {

            required : true,
            minlength :10,
            maxlength:10
        }
         ,
        airtimenumber2 : {
            required : true,
            minlength :10,
            maxlength:10
        } ,
    firstname2 : {
            required : true,
        },
        phonenumber2 : {
            required : true,
            minlength :10,
            maxlength:10
        },
        address2 : {
            required : true,
        },
        company2 : {
            required : true,
        },
        sitename2 : {
            required : true,
        }



    }
});



