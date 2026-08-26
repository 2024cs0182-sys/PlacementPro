// =========================================
// PLACEMENTPRO - MAIN JAVASCRIPT
// =========================================

console.log("PlacementPro frontend loaded.");


// =========================================
// PASSWORD VISIBILITY
// =========================================

function togglePassword(inputId, button) {

    const input = document.getElementById(inputId);

    if (!input) {
        return;
    }

    if (input.type === "password") {

        input.type = "text";
        button.textContent = "Hide";

    } else {

        input.type = "password";
        button.textContent = "Show";

    }
}


// =========================================
// MESSAGE FUNCTION
// =========================================

function showMessage(element, text, type) {

    if (!element) {
        return;
    }

    element.textContent = text;

    element.className =
        "form-message " + type;
}


// =========================================
// REGISTER
// =========================================

const registerForm =
    document.getElementById("registerForm");


if (registerForm) {

    registerForm.addEventListener(
        "submit",
        async function (event) {

            event.preventDefault();


            const name =
                document.getElementById(
                    "registerName"
                ).value.trim();


            const email =
                document.getElementById(
                    "registerEmail"
                ).value.trim();


            const password =
                document.getElementById(
                    "registerPassword"
                ).value;


            const confirmPassword =
                document.getElementById(
                    "confirmPassword"
                ).value;


            const terms =
                document.getElementById(
                    "terms"
                ).checked;


            const message =
                document.getElementById(
                    "registerMessage"
                );


            // VALIDATION

            if (password.length < 6) {

                showMessage(
                    message,
                    "Password must contain at least 6 characters.",
                    "error"
                );

                return;
            }


            if (password !== confirmPassword) {

                showMessage(
                    message,
                    "Passwords do not match.",
                    "error"
                );

                return;
            }


            if (!terms) {

                showMessage(
                    message,
                    "Please accept the terms and conditions.",
                    "error"
                );

                return;
            }


            showMessage(
                message,
                "Creating your account...",
                "success"
            );


            // SEND TO SPRING BOOT

            try {

                const response =
                    await fetch(
                        "http://https://placementpro-khij.onrender.com/api/students/register",
                        {
                            method: "POST",

                            headers: {
                                "Content-Type":
                                    "application/json"
                            },

                            body: JSON.stringify({

                                name: name,

                                email: email,

                                password: password

                            })
                        }
                    );


                const data =
                    await response.json();


                if (!response.ok) {

                    showMessage(
                        message,
                        data.message ||
                        "Registration failed.",
                        "error"
                    );

                    return;
                }


                showMessage(
                    message,
                    "Account created successfully! Redirecting...",
                    "success"
                );


                setTimeout(
                    function () {

                        window.location.href =
                            "login.html";

                    },
                    1200
                );


            } catch (error) {

                console.error(
                    "Registration error:",
                    error
                );


                showMessage(
                    message,
                    "Cannot connect to backend. Make sure Spring Boot is running.",
                    "error"
                );

            }

        }
    );

}



// =========================================
// LOGIN
// =========================================

const loginForm =
    document.getElementById("loginForm");


if (loginForm) {

    loginForm.addEventListener(
        "submit",
        async function (event) {

            event.preventDefault();


            const email =
                document.getElementById(
                    "loginEmail"
                ).value.trim();


            const password =
                document.getElementById(
                    "loginPassword"
                ).value;


            const message =
                document.getElementById(
                    "loginMessage"
                );


            if (
                email === "" ||
                password === ""
            ) {

                showMessage(
                    message,
                    "Please enter your email and password.",
                    "error"
                );

                return;
            }


            showMessage(
                message,
                "Logging in...",
                "success"
            );


            // SEND LOGIN TO SPRING BOOT

            try {

                const response =
                    await fetch(
                        "http://https://placementpro-khij.onrender.com/api/students/login",
                        {
                            method: "POST",

                            headers: {
                                "Content-Type":
                                    "application/json"
                            },

                            body: JSON.stringify({

                                email: email,

                                password: password

                            })
                        }
                    );


                const data =
                    await response.json();


                if (!response.ok) {

                    showMessage(
                        message,
                        data.message ||
                        "Invalid email or password.",
                        "error"
                    );

                    return;
                }


                // SAVE LOGGED-IN STUDENT

                localStorage.setItem(
                    "student",
                    JSON.stringify(data)
                );


                showMessage(
                    message,
                    "Login successful! Opening dashboard...",
                    "success"
                );


                setTimeout(
                    function () {

                        window.location.href =
                            "dashboard.html";

                    },
                    700
                );


            } catch (error) {

                console.error(
                    "Login error:",
                    error
                );


                showMessage(
                    message,
                    "Cannot connect to backend. Make sure Spring Boot is running.",
                    "error"
                );

            }

        }
    );

}



// =========================================
// BACKEND CONNECTION TEST
// =========================================

async function testBackend() {

    const message =
        document.getElementById(
            "backendMessage"
        );


    if (!message) {
        return;
    }


    message.textContent =
        "Connecting to backend...";


    try {

        const response =
            await fetch(
                "http://https://placementpro-khij.onrender.com/api/hello"
            );


        if (!response.ok) {

            throw new Error(
                "Backend request failed"
            );

        }


        const data =
            await response.text();


        message.textContent =
            data;


        console.log(
            "Backend response:",
            data
        );


    } catch (error) {

        console.error(error);


        message.textContent =
            "Unable to connect to backend.";

    }

}