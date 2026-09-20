# 🚆 RailEase

<div align="center">

### 🎫 Smart Train Reservation System

A Java-based train reservation system with a modern web interface for searching trains, booking tickets, managing seats, and viewing booking details.

<br>

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

<br>

</div>

---

## 🌟 Project Overview

**RailEase** is a train reservation application built using **Java, Object-Oriented Programming, Java Collections, HTML, CSS, and JavaScript**.

The application provides a complete train booking flow through a web interface. Users can search for trains, check seat availability, enter passenger details, book tickets, receive automatic coach and berth allocation, select a payment method, view booking details, and cancel reservations.

The Java backend handles the reservation logic and communicates with the web frontend through API endpoints.

> **Payment Note:** Payment is simulated for demonstration purposes. No real financial transaction is processed.

---

## ✨ Features

### 🚆 Train Search & Information

- Search available trains
- View train number and train name
- View source and destination
- View departure and arrival times
- Check available seats

### 🎫 Ticket Reservation

- Enter passenger details
- Generate a unique booking ID
- Automatically allocate an available seat
- Automatically assign coach and berth
- Display complete booking confirmation

### 💺 Seat Management

- Track booked and available seats
- Automatically find the next available seat
- Assign coach based on seat number
- Assign berth type automatically
- View seat availability through the seat map

### 🛏️ Berth Allocation

The system supports:

- Lower
- Middle
- Upper
- Side Lower
- Side Upper

### 💳 Demo Payment

Users can select:

- UPI
- Debit/Credit Card
- Net Banking
- Cash

The booking confirmation displays the fare, selected payment method, and payment status.

### 📄 Booking Management

- View booking details
- View passenger information
- View train information
- View coach and berth
- View fare and payment details
- Cancel bookings
- Release cancelled seats back into availability

---

## 🛠️ Tech Stack

| Category | Technologies |
|---|---|
| **Programming Language** | Java |
| **Backend** | Java HTTP Server |
| **Frontend** | HTML5, CSS3, JavaScript |
| **Programming Concepts** | OOP, Collections, Encapsulation, Method Overloading |
| **Data Structure** | ArrayList |
| **Version Control** | Git, GitHub |
| **Development Tools** | VS Code / IntelliJ IDEA |

---

## 🧠 Core Java Concepts

### Object-Oriented Programming

The application is divided into dedicated classes:

```text
Train
Passenger
Booking
ReservationSystem
RailEaseServer
        👤 User
          │
          ▼
   🌐 Web Interface
          │
          ▼
     🔎 Search Train
          │
          ▼
     🚆 Select Train
          │
          ▼
   👤 Passenger Details
          │
          ▼
    💺 Seat Allocation
          │
          ▼
    🛏️ Berth Allocation
          │
          ▼
      💳 Payment
          │
          ▼
   🎫 Booking Confirmation
          │
       ┌──┴──┐
       ▼     ▼
   📄 View  ❌ Cancel
   Booking   Booking

                         RailEase
                            │
             ┌──────────────┴──────────────┐
             │                             │
       🌐 Frontend                     ☕ Backend
             │                             │
     ┌───────┼───────┐             ┌──────┼─────────┐
     │       │       │             │      │         │
    HTML    CSS     JS           Train  Booking  Passenger
                                      │
                                      ▼
                              ReservationSystem
                                      │
                                      ▼
                               RailEaseServer


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
└── README.md
