# 🚆 RailEase

<div align="center">

## 🎫 Smart Train Reservation System

**A Java-based train reservation system with a modern web interface for searching trains, booking tickets, managing seats, and viewing booking details.**

<br>

![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk)
![HTML](https://img.shields.io/badge/HTML-5-E34F26?style=for-the-badge&logo=html5)
![CSS](https://img.shields.io/badge/CSS-3-1572B6?style=for-the-badge&logo=css3)
![JavaScript](https://img.shields.io/badge/JavaScript-ES6+-F7DF1E?style=for-the-badge&logo=javascript)
![Git](https://img.shields.io/badge/Git-Version_Control-F05032?style=for-the-badge&logo=git)

<br>

### 🌐 Search • Book • Pay • Manage

</div>

---

## 📌 About The Project

**RailEase** is a train seat reservation application developed using **Java, OOP, Collections, HTML, CSS, and JavaScript**.

The project provides a simple web-based experience where users can search trains, view availability, enter passenger details, book seats, select a payment method, and manage their bookings.

The Java backend handles the core reservation logic while the frontend provides an interactive browser-based interface.

> 💡 **Payment Note:** The payment system is a demo/simulated payment flow created for project demonstration. No real financial transaction is processed.

---

# ✨ Features

<div align="center">

| 🚆 Train Management | 🎫 Reservation | 💳 Payment | 🪑 Seat Management |
|:---:|:---:|:---:|:---:|
| Search Trains | Book Tickets | UPI | Seat Availability |
| View Train Details | Passenger Details | Card | Coach Allocation |
| View Routes | Booking Confirmation | Net Banking | Berth Allocation |
| Departure & Arrival | Booking ID | Cash | Seat Map |

</div>

### 🔎 Train Search
- Search available trains
- View train number and name
- Check source and destination
- View departure and arrival times
- Check available seats

### 🎫 Ticket Booking
- Enter passenger information
- Select a train
- Automatically allocate a seat
- Generate booking ID
- Display complete ticket details

### 💺 Automatic Seat Allocation
RailEase automatically finds the first available seat and assigns:

- Coach
- Seat number
- Berth type

Supported berth types:

`Lower` • `Middle` • `Upper` • `Side Lower` • `Side Upper`

### 💳 Demo Payment
Users can select:

- 💠 UPI
- 💳 Debit/Credit Card
- 🏦 Net Banking
- 💵 Cash

The system displays the fare and payment status after booking.

### 📄 Booking Management
Users can:

- View booking details
- Check passenger information
- Check train information
- View seat and coach
- View payment details
- Cancel a booking

### 🪑 Seat Map
The application displays the availability of seats for a selected train.

### ❌ Cancellation
When a booking is cancelled, the allocated seat becomes available again.

---

# 🖥️ Application Screenshots

## 🏠 Home Page

<img src="screenshots/home.png" alt="RailEase Home Page" width="100%">

<br>

## ⭐ Popular Trains

<img src="screenshots/popular-trains.png" alt="RailEase Popular Trains" width="100%">

<br>

## 🎫 Booking & Payment

<img src="screenshots/booking.png" alt="RailEase Booking" width="100%">

<br>

## ✅ Booking Confirmation

<img src="screenshots/confirmation.png" alt="RailEase Booking Confirmation" width="100%">

<br>

## 💺 Seat Map

<img src="screenshots/seat-map.png" alt="RailEase Seat Map" width="100%">

---

# 🛠️ Tech Stack

### ☕ Backend

- **Java**
- **Object-Oriented Programming**
- **Java Collections**
- **Java HTTP Server**
- **API Endpoints**

### 🎨 Frontend

- **HTML5**
- **CSS3**
- **JavaScript**

### 🔧 Tools

- **Git**
- **GitHub**
- **VS Code / IntelliJ IDEA**

---

# 🧠 Core Java Concepts Used

RailEase was designed using practical Java concepts.

### 🔹 Object-Oriented Programming

The application is divided into multiple classes:

```text
Train
Passenger
Booking
ReservationSystem
RailEaseServer
```

Concepts used:

- Encapsulation
- Classes & Objects
- Constructors
- Methods
- Object Relationships

### 🔹 Java Collections

`ArrayList` is used to maintain:

```text
Train List
Booking List
```

### 🔹 Method Overloading

Different versions of the `bookTicket()` method are used to support different booking flows.

### 🔹 Data Handling

The system maintains train, passenger, booking, seat, coach, fare and payment information through Java objects.

---

# 🔄 How RailEase Works

```text
              👤 USER
                 │
                 ▼
        🌐 WEB INTERFACE
                 │
                 ▼
          🔎 SEARCH TRAIN
                 │
                 ▼
          🚆 SELECT TRAIN
                 │
                 ▼
        👤 PASSENGER DETAILS
                 │
                 ▼
          💺 SEAT ALLOCATION
                 │
                 ▼
           💳 DEMO PAYMENT
                 │
                 ▼
        🎫 BOOKING CONFIRMATION
                 │
          ┌──────┴──────┐
          ▼             ▼
     📄 VIEW BOOKING   ❌ CANCEL
```

---

# 🏗️ Project Architecture

```text
                         RailEase
                            │
             ┌──────────────┴──────────────┐
             │                             │
        🌐 Frontend                   ☕ Backend
             │                             │
      ┌──────┼──────┐              ┌───────┼────────┐
      │      │      │              │       │        │
    HTML    CSS    JS           Train   Booking  Passenger
                                      │
                                      ▼
                              ReservationSystem
                                      │
                                      ▼
                               RailEaseServer
```

---

# 📂 Project Structure

```text
RailEase/
│
├── Main.java
├── Train.java
├── Passenger.java
├── Booking.java
├── ReservationSystem.java
│
├── server/
│   └── RailEaseServer.java
│
├── web/
│   ├── index.html
│   ├── style.css
│   └── script.js
│
├── screenshots/
│   ├── home.png
│   ├── popular-trains.png
│   ├── booking.png
│   ├── confirmation.png
│   └── seat-map.png
│
└── README.md
```

---

# 💺 Seat Allocation Logic

RailEase automatically searches for an available seat.

### Booking Process

```text
1. Find selected train
          ↓
2. Check seat availability
          ↓
3. Find first available seat
          ↓
4. Calculate coach
          ↓
5. Calculate berth type
          ↓
6. Process demo payment
          ↓
7. Create booking
          ↓
8. Reduce available seat count
          ↓
9. Generate confirmation
```

### Berth Pattern

| Seat Position | Berth Type |
|---:|---|
| 1 | Lower |
| 2 | Middle |
| 3 | Upper |
| 4 | Lower |
| 5 | Middle |
| 6 | Upper |
| 7 | Side Lower |
| 8 | Side Upper |

The pattern repeats for subsequent seats.

---

# 💳 Demo Payment Flow

RailEase includes a simulated payment system.

```text
             Select Payment Method
                       │
       ┌───────────────┼───────────────┐
       │               │               │
      UPI             Card        Net Banking
       │               │               │
       └───────────────┼───────────────┘
                       │
                     Cash
                       │
                       ▼
               Payment Processing
                       │
                       ▼
                Payment Successful
                       │
                       ▼
                Ticket Confirmed
```

### Demo Fare

**₹500**

The booking confirmation displays:

```text
Booking ID
Passenger
Train
Source
Destination
Coach
Berth Number
Berth Type
Fare
Payment Method
Payment Status
```

---

# ▶️ Run The Project Locally

## 1️⃣ Clone the Repository

```bash
git clone <YOUR-GITHUB-REPOSITORY-URL>
```

```bash
cd RailEase
```

## 2️⃣ Compile

```bash
javac *.java server\RailEaseServer.java
```

## 3️⃣ Start The Server

```bash
java -cp ".;server" RailEaseServer
```

## 4️⃣ Open In Browser

```text
http://localhost:8080
```

🎉 The RailEase application should now be running locally.

---

# 📸 Screenshots Folder

Keep all screenshots inside:

```text
screenshots/
```

Recommended files:

```text
home.png
popular-trains.png
booking.png
payment.png
confirmation.png
seat-map.png
```

Make sure the filenames in the README exactly match the actual filenames.

---

# 🌱 Future Improvements

Some possible future enhancements:

- 🔐 User authentication
- 🗄️ Database integration
- 💰 Dynamic fare calculation
- 📧 Email ticket confirmation
- 🧾 Downloadable ticket/receipt
- 💳 Real payment gateway
- 📱 Better mobile responsiveness
- ☁️ Cloud deployment
- 🪑 Interactive visual seat selection
- 📊 Admin dashboard

---

# 🎯 What I Learned

Building RailEase helped me practice:

- ☕ Java programming
- 🧱 Object-Oriented Programming
- 📦 Java Collections
- 🧠 Problem-solving and logic building
- 💺 Seat allocation algorithms
- 🌐 Backend and frontend integration
- 🔗 API communication
- 🧪 Input validation
- 🔧 Git and GitHub
- 🖥️ Building a complete application from scratch

---

# 🚀 Project Highlights

<div align="center">

### 🎫 Complete Booking Flow

**Search → Select → Passenger Details → Seat Allocation → Payment → Confirmation**

### ☕ Java Backend

**OOP + Collections + Reservation Logic**

### 🌐 Web Interface

**HTML + CSS + JavaScript**

### 💳 Demo Payment

**UPI + Card + Net Banking + Cash**

</div>

---

# 👩‍💻 Author

### Shwarna Srivastava

**CSE (Data Science) Student**
---

<div align="center">

## 🚆 RailEase

### Making Train Reservation Simple, Clear & Convenient.

⭐ **If you like this project, consider giving the repository a star!**

</div>
