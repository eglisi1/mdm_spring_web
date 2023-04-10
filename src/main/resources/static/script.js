function process() {
    let inputText = document.getElementById("inputText").value;

    $.ajax({
        type: "POST",
        url: "sentimentThymeleaf",
        data: { text: inputText },
        success: function(data) {
            $("#result").html(data);
        }
    });
}