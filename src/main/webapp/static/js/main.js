
var editStorageMap = {};
var trackerTables = {};

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

function initTrackerDatetime() {
    $(function () {
        var date = moment();
        $('#datetimepicker').datetimepicker({
            viewMode: 'days',
            sideBySide: true,
            format:'DD.MM.YYYY',
            useCurrent: true,
            defaultDate: date,
        });
        $('#datetimepicker').keyup(function() {
            updateAllTables();
        });
        $("#datetimepicker").on("change.datetimepicker", function (e) {
            updateAllTables();
        });

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

function addFood(dateString,foodId,amount,repastTypeId,successFunction,errorFunction){
    $.ajax({
        type: "POST",
        url: context_path+"/tracker/eaten",
        dataType: "json",
        data: {
            "dateString":dateString,
            "foodId":foodId,
            "amount":amount,
            "repastTypeId":repastTypeId,
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

function initEatenFood(tableSelector,repast) {
    trackerTables[repast] = new Tabulator(tableSelector, {
        height:"150px",
        layout:"fitColumns",
        placeholder:"No Data Set",
        columns:[
            {title:"Food", field:"title", sorter:"string", width:200},
            {title:"Weight", field:"portionWeight", sorter:"string"},
            {title:"Fats", field:"fats", sorter:"string"},
            {title:"Proteins", field:"proteins", align:"center", width:100},
            {title:"Carb.", field:"carbohydrates", sorter:"string", sortable:false},
            {title:"Kcal.", field:"kilocalories",  align:"center"},
        ],
    });
}

function applyData(repast,date) {
    trackerTables[repast].setData(context_path+"/tracker/eaten?date="+date+"&repast="+repast);
}


function updateTableData(repast) {
    var date = $("#datetimepicker").data("datetimepicker").date().format('DD.MM.YYYY');
    if(typeof date == "undefined"){
        date = moment().format("DD.MM.YYYY");
    }
    applyData(repast,date);
}
function updateAllTables() {
    updateTableData(1);
    updateTableData(2);
    updateTableData(3);
    updateTableData(4);
}
function initAllTables() {
    initEatenFood("#breakfast-table",1);
    initEatenFood("#lunch-table",2);
    initEatenFood("#dinner-table",3);
    initEatenFood("#snack-table",4);
    setTimeout(function (){updateAllTables();}, 500);
}

function initTrackerControls() {

    $(".eaten-control").each(function () {
        var repastId = $(this).attr("data-repast");
        var selectedFoodId;
        var searchInput = $(this).find(".food-search");
        var addButton = $(this).find(".food-add");
        var amountField = $(this).find(".food-amount");

        var foodList = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.obj.whitespace('title'),
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            sufficient: 100,
            remote: {
                url: context_path+'/food/search?query=%QUERY',
                wildcard: '%QUERY',
                rateLimitWait:20,
            }
        });

        searchInput.typeahead(
            {
                hint: true,
                highlight: true,
                minLength: 2
            },{
                name: 'foodList',
                display: 'title',
                limit:10,
                source: foodList,
            }
        );

        searchInput.bind('typeahead:select', function (ev, suggestion) {
            if (typeof suggestion != 'undefined') {
                selectedFoodId = suggestion.id;
                amountField.val(suggestion.portionWeight);
            }
        });

        addButton.click(function(){
            var dateString = $("#datetimepicker").data("datetimepicker").date().format('DD.MM.YYYY');
            var amount = amountField.val();
            addFood(dateString,selectedFoodId,amount,repastId,function (data) {
                updateTableData(repastId);
                amountField.val("");
                searchInput.val("");
            },function () {

            });

        });

    });
}

$( document ).ready(function() {
    initTrackerDatetime();
    initEditButtons();
    initCancelButtons();
    initApplyButtons();
    initAllTables();
    initTrackerControls();
});

