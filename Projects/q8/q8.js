function getUsername() {
    return document.getElementById("username").value.trim();
}

function displayOutput(data) {
    document.getElementById("output").innerText = JSON.stringify(data, null, 2);
}

function displayError(message) {
    document.getElementById("output").innerText = `Error: ${message}`;
}

// Approach 1: async/await
async function fetchReposAsync() {
    const user = getUsername();
    const url = `https://api.github.com/users/${user}/repos`;

    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`User "${user}" not found or API limit reached.`);
        }
        const data = await response.json();
        displayOutput(data);
    } catch (error) {
        displayError(error.message);
    }
}

// Approach 2: .then() chaining
function fetchReposThen() {
    const user = getUsername();
    const url = `https://api.github.com/users/${user}/repos`;

    fetch(url)
        .then(res => {
            if (!res.ok) {
                throw new Error(`Could not find user "${user}" or API failed.`);
            }
            return res.json();
        })
        .then(data => displayOutput(data))
        .catch(error => displayError(error.message));
}
