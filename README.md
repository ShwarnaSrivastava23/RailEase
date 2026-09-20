# 🚆 RailEase — Train Seat Reservation System

<p align="center">
  <strong>A Java-based train reservation system with a web interface for searching trains, booking tickets, managing seats, and handling demo payments.</strong>
</p>

<p align="center">
  <a href="YOUR_LIVE_DEMO_LINK">🌐 Live Demo</a> •
  <a href="YOUR_GITHUB_REPO_LINK">💻 Source Code</a>
</p>

---

## 📌 About The Project

**RailEase** is a train seat reservation system built using **Java, Object-Oriented Programming, Collections, and a lightweight web interface**.

The application allows users to search for trains, select a train, enter passenger details, book a ticket, receive automatic seat and berth allocation, view booking details, check seat availability, and cancel bookings.

The project was built to understand how a Java-based backend can be connected with a web frontend while applying core programming and OOP concepts.

---

## ✨ Features

### 🚆 Train Management
- View available trains
- Search trains by source and destination
- Filter trains by train type
- Display departure and arrival times
- Display available seats

### 🎫 Ticket Booking
- Select a train
- Enter passenger details
- Generate unique booking ID
- Automatic seat allocation
- Automatic coach allocation
- Automatic berth type allocation

### 💺 Seat Management
- View complete seat map
- Display booked and available seats
- Show coach and berth type
- Automatically update available seats after booking
- Release seat after cancellation

### 💳 Demo Payment
- UPI
- Debit/Credit Card
- Net Banking
- Cash
- Fixed demo fare of ₹500
- Payment status display

> ⚠️ This project uses a **demo payment system**. No real financial transactions are performed.

### 📋 Booking Management
- View complete booking details
- Search booking using Booking ID
- View passenger information
- View train information
- View seat and berth information
- View payment details

### ❌ Cancellation
- Cancel booking using Booking ID
- Automatically release the reserved seat
- Update available seat count

---

## 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| ☕ Java | Backend logic and application development |
| 🧩 OOP | Classes, objects, encapsulation and modular design |
| 📦 Java Collections | Managing trains and bookings |
| 🌐 HTML | Web page structure |
| 🎨 CSS | User interface styling |
| ⚡ JavaScript | Frontend interactions and API requests |
| 🔗 REST-style APIs | Communication between frontend and Java backend |
| 🖥️ Java HTTP Server | Local web server |
| 🛠️ Git | Version control |
| 🐙 GitHub | Source code hosting |

---

## 🏗️ Project Structure

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
├── .gitignore
└── README.md
