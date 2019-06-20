
var editStorageMap = {};

function enableEditing(editButton,inputField,applyButton,cancelButton){
        editButton.addClass("d-none");
        inputField.prop( "disabled", false );
        applyButton.removeClass("d-none");
        cancelButton.removeClass("d-none");
}

function disableEditing(editButton,inputField,applyButton,cancelButton){
        cancelButton.addClass("d-none");
        inputField.prop( "disabled", true );
        applyButton.addClass("d-none");
        editButton.removeClass("d-none");
}

function removeTooltips(inputField){
        inputField.removeClass("is-valid");
        inputField.removeClass("is-invalid");
}

function removeTooltipsAfter(inputField,time) {
    setTimeout(function (){removeTooltips(inputField);}, time);
}



function initEditButtons() {
    $("button.edit-action").click(function(){
        var editButton=$(this);
        var editPanel = editButton.parent();
        var editPanelId = editPanel.attr('id');
        var inputField = $( "[aria-describedby="+editPanelId+"]" );
        var applyButton = editPanel.find(".apply-action");
        var cancelButton = editPanel.find(".cancel-action");

        editStorageMap[editPanelId] = inputField.val();

        enableEditing(editButton,inputField,applyButton,cancelButton);
    });
}

function initCancelButtons() {
    $("button.cancel-action").click(function(){
        var cancelButton=$(this);
        var editPanel = cancelButton.parent();
        var editPanelId = editPanel.attr('id');
        var inputField = $( "[aria-describedby="+editPanelId+"]" );
        var applyButton = editPanel.find(".apply-action");
        var editButton = editPanel.find(".edit-action");

        inputField.val(editStorageMap[editPanelId]);

        disableEditing(editButton,inputField,applyButton,cancelButton);
        removeTooltips(inputField);
    });
}

function initApplyButtons() {
    $("button.apply-action").click(function(){
        var applyButton=$(this);
        var editPanel = applyButton.parents(".row");
        var editPanelId = editPanel.find(".input-group-append").attr('id');
        var inputField = $( "[aria-describedby="+editPanelId+"]" );
        var cancelButton = editPanel.find(".cancel-action");
        var editButton = editPanel.find(".edit-action");
        var fieldName = inputField.attr("name");
        var value = inputField.val();
        var endpoint = inputField.attr("data-controller"); 
        var statusContainer = editPanel.find("div.status-container");
        
        applyField(endpoint,fieldName,value,function (data){
            if(data.status=="ok"){
                inputField.removeClass("is-invalid");
                alertResponseText(statusContainer,data);
                disableEditing(editButton,inputField,applyButton,cancelButton);
                delete editStorageMap[editPanelId];
                inputField.addClass("is-valid");
            }else{
                inputField.removeClass("is-valid");
                alertResponseText(statusContainer,data);
                inputField.addClass("is-invalid");
            }
            removeTooltipsAfter(inputField,10000);
            //clearResponse();
        },
        function (data){
            inputField.removeClass("is-valid");
            alertResponseText(statusContainer,data);
            inputField.addClass("is-invalid");
        }
        );
    });
}

    function alertResponse(object,response){
        if(typeof response.statusText != 'undefined'){
            object.html('<div class="alert alert-danger" role="alert">'+response.statusText + "\r\n" + response.responseText+'</div>');
        }else
        if(response.status=="ok"){
            object.html('<div class="alert alert-success" role="alert">'+response.message+'</div>');
        }else if(response.status=="error"){
            object.html('<div class="alert alert-warning" role="alert">'+response.message+'</div>');            
        }
    };
    function alertResponseText(object,response){
        if(typeof response.statusText != 'undefined'){
            object.text(response.responseText);
        }else
        if(response.status=="ok"){
            object.text(response.message);
        }else if(response.status=="error"){
            object.text(response.message);            
        }
    };
    
    function clearResponse(object){
        object.html('');
    }

function applyField(endpoint,fieldName,value,successFunction,errorFunction){
    $.ajax({
        type: "POST",
        url: endpoint,
        dataType: "json",
        data: {
            "fieldName":fieldName,
            "value":value,
            "_csrf":_csrf,
        },
        success: function(data){
            successFunction.call(this,data);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            errorFunction.call(this,xhr);
        },
    });
};

var foodList = new Bloodhound({
    datumTokenizer: Bloodhound.tokenizers.obj.whitespace('data'),
    queryTokenizer: Bloodhound.tokenizers.whitespace,
    sufficient: 100,
    remote: {
        url: '/food/food/search?query=%QUERY',
        wildcard: '%QUERY',
        rateLimitWait:20,
    }
});


$('#foodSearch-typeahead').typeahead(
    {
       // menu: ,
        hint: true,
        highlight: true,
        minLength: 2
    },{
        name: 'foodList',
        display: 'data',
        limit:100,
        source: foodList,
        templates: {
            suggestion:function(data) {
                $('#typeahead-target').html(data);
                return data;
            }
        }
    }
);


$( document ).ready(function() {
    initEditButtons();
    initCancelButtons();
    initApplyButtons();
});

