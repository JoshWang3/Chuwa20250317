function generateLuckyNumber() {
    const input = document.getElementById("userInput").value.trim();
    const now = new Date();
    const baseString = input + now.toISOString();

    let hash = 0;
    for (let i = 0; i < baseString.length; i++) {
        hash = (hash << 5) - hash + baseString.charCodeAt(i);
        hash |= 0;
    }

    const luckyNumber = Math.abs(hash % 100) + 1;
    document.getElementById("result").innerText = `Your Lucky Number is: ${luckyNumber}`;
}

document.getElementById("userInput").addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        generateLuckyNumber();
    }
});
