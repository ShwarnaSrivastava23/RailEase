// =========================
// SELECTED TRAIN
// =========================

let selectedTrainNumber = null;


// =========================
// SEARCH TRAINS
// =========================

function searchTrains() {

    const source =
        document.getElementById("source").value.trim();

    const destination =
        document.getElementById("destination").value.trim();

    const trainType =
        document.getElementById("trainType").value;


    if (source === "" || destination === "") {

        alert(
            "Please enter source and destination."
        );

        return;
    }


    const url =
        "/api/search?source=" +
        encodeURIComponent(source) +
        "&destination=" +
        encodeURIComponent(destination) +
        "&type=" +
        encodeURIComponent(trainType);


    const resultsContainer =
        document.getElementById("trainResults");

    const resultsMessage =
        document.getElementById("resultsMessage");


    resultsMessage.innerText =
        "Searching for trains...";


    resultsContainer.innerHTML = `

        <div class="search-train-card">

            <div class="search-train-icon">
                🔄
            </div>

            <div class="search-train-info">

                <h3>
                    Searching trains...
                </h3>

                <p>
                    Please wait while we find
                    available trains.
                </p>

            </div>

        </div>

    `;


    fetch(url)

        .then(response => {

            if (!response.ok) {

                throw new Error(
                    "Server returned an error."
                );
            }

            return response.json();

        })


        .then(trains => {

            resultsContainer.innerHTML = "";


            if (trains.length === 0) {

                resultsMessage.innerText =
                    "No trains found for " +
                    source +
                    " → " +
                    destination;


                resultsContainer.innerHTML = `

                    <div class="search-train-card">

                        <div class="search-train-icon">
                            🔍
                        </div>

                        <div class="search-train-info">

                            <h3>
                                No trains found
                            </h3>

                            <p>
                                Try another route
                                or train type.
                            </p>

                        </div>

                    </div>

                `;

                return;
            }


            resultsMessage.innerText =
                trains.length +
                " train(s) found for " +
                source +
                " → " +
                destination;


            trains.forEach(train => {

                const card =
                    document.createElement("div");


                card.className =
                    "search-train-card";


                card.innerHTML = `

                    <div class="search-train-icon">
                        🚆
                    </div>


                    <div class="search-train-info">

                        <h3>
                            ${train.trainName}
                        </h3>

                        <p>
                            Train Number:
                            ${train.trainNumber}
                        </p>

                        <p>
                            ${train.source}
                            →
                            ${train.destination}
                        </p>

                        <span class="train-type">
                            ${train.trainType}
                        </span>

                    </div>


                    <div class="search-train-seats">

                        <strong>
                            ${train.availableSeats}
                        </strong>

                        <small>
                            seats available
                        </small>

                    </div>


                    <button
                        class="result-book-button"
                        onclick="selectTrain(${train.trainNumber})">

                        Book Now

                    </button>

                `;


                resultsContainer.appendChild(card);

            });


            document
                .querySelector(".results-section")
                .scrollIntoView({
                    behavior: "smooth"
                });

        })


        .catch(error => {

            console.error(error);

            resultsMessage.innerText =
                "Unable to connect to server.";


            resultsContainer.innerHTML = `

                <div class="search-train-card">

                    <div class="search-train-icon">
                        ⚠️
                    </div>

                    <div class="search-train-info">

                        <h3>
                            Server Connection Error
                        </h3>

                        <p>
                            Please make sure the
                            RailEase Java server
                            is running.
                        </p>

                    </div>

                </div>

            `;

        });

}


// =========================
// SELECT TRAIN
// =========================

function selectTrain(trainNumber) {

    selectedTrainNumber = trainNumber;


    const bookingSection =
        document.getElementById(
            "bookingFormSection"
        );


    const trainNameElement =
        document.getElementById(
            "selectedTrainName"
        );


    const trainNumberElement =
        document.getElementById(
            "selectedTrainNumber"
        );


    const trainCards =
        document.querySelectorAll(
            ".search-train-card"
        );


    let selectedTrainName =
        "Train " + trainNumber;


    trainCards.forEach(card => {

        const text =
            card.innerText;


        if (
            text.includes(
                "Train Number: " +
                trainNumber
            )
        ) {

            const heading =
                card.querySelector("h3");


            if (heading) {

                selectedTrainName =
                    heading.innerText;

            }

        }

    });


    /*
     * Also check popular train cards.
     */

    if (
        selectedTrainName ===
        "Train " + trainNumber
    ) {

        const popularCards =
            document.querySelectorAll(
                ".train-card"
            );


        popularCards.forEach(card => {

            const text =
                card.innerText;


            if (
                text.includes(
                    "#" + trainNumber
                )
            ) {

                const heading =
                    card.querySelector("h3");


                if (heading) {

                    selectedTrainName =
                        heading.innerText;

                }

            }

        });

    }


    trainNameElement.innerText =
        selectedTrainName;


    trainNumberElement.innerText =
        trainNumber;


    /*
     * Clear old passenger details.
     */

    document.getElementById(
        "passengerName"
    ).value = "";


    document.getElementById(
        "passengerAge"
    ).value = "";


    document.getElementById(
        "passengerGender"
    ).value = "";


    document.getElementById(
        "bookingMessage"
    ).innerHTML = "";


    /*
     * Add payment method selector.
     */

    createPaymentSelector();


    /*
     * Show booking form.
     */

    bookingSection.style.display =
        "block";


    /*
     * Scroll to booking form.
     */

    bookingSection.scrollIntoView({
        behavior: "smooth"
    });

}


// =========================
// CREATE PAYMENT SELECTOR
// =========================

function createPaymentSelector() {

    const bookingForm =
        document.getElementById(
            "bookingFormSection"
        );

    if (!bookingForm) {
        return;
    }


    /*
     * If payment selector already exists,
     * don't create another one.
     */

    let existingPayment =
        document.getElementById(
            "paymentMethod"
        );

    if (existingPayment) {
        return;
    }


    /*
     * Find gender field as the position
     * where payment section should appear.
     */

    const genderField =
        document.getElementById(
            "passengerGender"
        );


    let parent =
        genderField
            ? genderField.parentElement
            : null;


    if (!parent) {

        parent = bookingForm;

    }


    const paymentContainer =
        document.createElement("div");


    paymentContainer.id =
        "paymentMethodContainer";


    paymentContainer.style.marginTop =
        "15px";


    paymentContainer.innerHTML = `

        <label
            for="paymentMethod"
            style="
                display:block;
                margin-bottom:8px;
                font-weight:600;
            "
        >
            Payment Method
        </label>

        <select
            id="paymentMethod"
            style="
                width:100%;
                padding:10px;
                border-radius:6px;
                border:1px solid #ccc;
                font-size:15px;
            "
        >

            <option value="">
                Select Payment Method
            </option>

            <option value="UPI">
                UPI
            </option>

            <option value="Debit/Credit Card">
                Debit/Credit Card
            </option>

            <option value="Net Banking">
                Net Banking
            </option>

            <option value="Cash">
                Cash
            </option>

        </select>

        <p
            style="
                margin-top:6px;
                font-size:13px;
                color:#777;
            "
        >
            Demo payment only. No real money will be charged.
        </p>

    `;


    /*
     * Put payment section after gender field.
     */

    if (parent.nextSibling) {

        parent.parentNode.insertBefore(
            paymentContainer,
            parent.nextSibling
        );

    } else {

        parent.parentNode.appendChild(
            paymentContainer
        );

    }

}


// =========================
// CONFIRM BOOKING
// =========================

function confirmBooking() {

    if (selectedTrainNumber === null) {

        alert(
            "Please select a train first."
        );

        return;
    }


    const name =
        document
            .getElementById("passengerName")
            .value
            .trim();


    const age =
        document
            .getElementById("passengerAge")
            .value;


    const gender =
        document
            .getElementById("passengerGender")
            .value;


    const paymentMethodElement =
        document.getElementById(
            "paymentMethod"
        );


    const paymentMethod =
        paymentMethodElement
            ? paymentMethodElement.value
            : "";


    // =========================
    // VALIDATION
    // =========================

    if (name === "") {

        alert(
            "Please enter passenger name."
        );

        return;
    }


    if (
        age === "" ||
        Number(age) < 1 ||
        Number(age) > 120
    ) {

        alert(
            "Please enter a valid age."
        );

        return;
    }


    if (gender === "") {

        alert(
            "Please select gender."
        );

        return;
    }


    if (paymentMethod === "") {

        alert(
            "Please select a payment method."
        );

        return;
    }


    const bookingMessage =
        document.getElementById(
            "bookingMessage"
        );


    bookingMessage.innerHTML = `

        <div class="booking-success">

            <h3>
                🔄 Processing Payment...
            </h3>

            <p>
                Payment Method:
                <strong>
                    ${paymentMethod}
                </strong>
            </p>

            <p>
                Fare: <strong>₹500</strong>
            </p>

            <p>
                Please wait while we process
                the demo payment.
            </p>

        </div>

    `;


    /*
     * Create booking API URL.
     *
     * IMPORTANT:
     * paymentMethod is now sent to Java server.
     */

    const url =
        "/api/book?trainNumber=" +
        encodeURIComponent(
            selectedTrainNumber
        ) +

        "&name=" +
        encodeURIComponent(name) +

        "&age=" +
        encodeURIComponent(age) +

        "&gender=" +
        encodeURIComponent(gender) +

        "&paymentMethod=" +
        encodeURIComponent(paymentMethod);


    /*
     * Send request to Java server.
     */

    fetch(url)

        .then(response => {

            return response.json()
                .then(data => {

                    if (!response.ok) {

                        throw new Error(
                            data.error ||
                            "Booking failed."
                        );

                    }

                    return data;

                });

        })


        .then(data => {

            /*
             * Booking successful.
             */

            bookingMessage.innerHTML = `

                <div class="booking-success">

                    <h3>
                        🎉 Booking Confirmed!
                    </h3>

                    <p>
                        Your ticket has been
                        successfully booked.
                    </p>


                    <div class="booking-ticket">

                        <p>
                            <strong>
                                Booking ID
                            </strong>
                            <br>
                            ${data.bookingId}
                        </p>


                        <p>
                            <strong>
                                Passenger
                            </strong>
                            <br>
                            ${data.passenger}
                        </p>


                        <p>
                            <strong>
                                Train
                            </strong>
                            <br>
                            ${data.trainName}
                        </p>


                        <p>
                            <strong>
                                Train Number
                            </strong>
                            <br>
                            ${data.trainNumber}
                        </p>


                        <p>
                            <strong>
                                From
                            </strong>
                            <br>
                            ${data.source}
                        </p>


                        <p>
                            <strong>
                                To
                            </strong>
                            <br>
                            ${data.destination}
                        </p>


                        <p>
                            <strong>
                                Departure
                            </strong>
                            <br>
                            ${data.departureTime}
                        </p>


                        <p>
                            <strong>
                                Arrival
                            </strong>
                            <br>
                            ${data.arrivalTime}
                        </p>


                        <p>
                            <strong>
                                Coach
                            </strong>
                            <br>
                            ${data.coach}
                        </p>


                        <p>
                            <strong>
                                Seat Number
                            </strong>
                            <br>
                            ${data.seatNumber}
                        </p>


                        <p>
                            <strong>
                                Berth Type
                            </strong>
                            <br>
                            ${data.berthType}
                        </p>


                        <p>
                            <strong>
                                Fare
                            </strong>
                            <br>
                            ₹${data.fare}
                        </p>


                        <p>
                            <strong>
                                Payment Method
                            </strong>
                            <br>
                            ${data.paymentMethod}
                        </p>


                        <p>
                            <strong>
                                Payment Status
                            </strong>
                            <br>
                            ${data.paymentStatus}
                        </p>

                    </div>


                    <p>
                        Please save your
                        Booking ID:
                        <strong>
                            ${data.bookingId}
                        </strong>
                    </p>

                </div>

            `;


            /*
             * Clear selected train.
             */

            selectedTrainNumber = null;


            /*
             * Refresh the search results
             * so available seats decrease.
             */

            refreshSearchResults();

        })


        .catch(error => {

            console.error(error);


            bookingMessage.innerHTML = `

                <div class="booking-error">

                    <h3>
                        ❌ Booking Failed
                    </h3>

                    <p>
                        ${error.message}
                    </p>

                </div>

            `;

        });

}


// =========================
// REFRESH SEARCH RESULTS
// =========================

function refreshSearchResults() {

    const source =
        document
            .getElementById("source")
            .value
            .trim();


    const destination =
        document
            .getElementById("destination")
            .value
            .trim();


    if (
        source === "" ||
        destination === ""
    ) {

        return;

    }


    const trainType =
        document
            .getElementById("trainType")
            .value;


    const url =
        "/api/search?source=" +
        encodeURIComponent(source) +
        "&destination=" +
        encodeURIComponent(destination) +
        "&type=" +
        encodeURIComponent(trainType);


    fetch(url)

        .then(response =>
            response.json()
        )

        .then(trains => {

            const resultsContainer =
                document.getElementById(
                    "trainResults"
                );


            resultsContainer.innerHTML = "";


            trains.forEach(train => {

                const card =
                    document.createElement(
                        "div"
                    );


                card.className =
                    "search-train-card";


                card.innerHTML = `

                    <div class="search-train-icon">
                        🚆
                    </div>


                    <div class="search-train-info">

                        <h3>
                            ${train.trainName}
                        </h3>

                        <p>
                            Train Number:
                            ${train.trainNumber}
                        </p>

                        <p>
                            ${train.source}
                            →
                            ${train.destination}
                        </p>

                        <span class="train-type">
                            ${train.trainType}
                        </span>

                    </div>


                    <div class="search-train-seats">

                        <strong>
                            ${train.availableSeats}
                        </strong>

                        <small>
                            seats available
                        </small>

                    </div>


                    <button
                        class="result-book-button"
                        onclick="selectTrain(${train.trainNumber})">

                        Book Now

                    </button>

                `;


                resultsContainer.appendChild(
                    card
                );

            });

        })


        .catch(error => {

            console.error(error);

        });

}


// =========================
// CLOSE BOOKING FORM
// =========================

function closeBookingForm() {

    document.getElementById(
        "bookingFormSection"
    ).style.display = "none";


    selectedTrainNumber = null;


    document
        .getElementById("search")
        .scrollIntoView({
            behavior: "smooth"
        });

}


// =========================
// SCROLL TO SEARCH
// =========================

function scrollToSearch() {

    document
        .getElementById("search")
        .scrollIntoView({
            behavior: "smooth"
        });

}