
var editStorageMap = {};

function initEditButtons() {
    $("button.edit-action").click(function(){
        var editButton=$(this);
        var editPanel = editButton.parent();
        var editPanelId = editPanel.attr('id');
        var inputField = $( "[aria-describedby="+editPanelId+"]" );
        var applyButton = editPanel.find(".apply-action");
        var cancelButton = editPanel.find(".cancel-action");

        editStorageMap[editPanelId] = inputField.val();

        editButton.addClass("d-none");
        inputField.prop( "disabled", false );
        applyButton.removeClass("d-none");
        cancelButton.removeClass("d-none");
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

        cancelButton.addClass("d-none");
        inputField.prop( "disabled", true );
        applyButton.addClass("d-none");
        editButton.removeClass("d-none");
    });
}


$( document ).ready(function() {
    initEditButtons();
    initCancelButtons();
});