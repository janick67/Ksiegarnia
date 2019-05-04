-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 04 Maj 2019, 12:45
-- Wersja serwera: 10.1.37-MariaDB
-- Wersja PHP: 7.1.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `bookstore`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `books`
--

CREATE TABLE `books` (
  `ID` int(11) NOT NULL,
  `title` varchar(30) NOT NULL,
  `author` varchar(30) NOT NULL,
  `instock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `books`
--

INSERT INTO `books` (`ID`, `title`, `author`, `instock`) VALUES
(1, 'Pan Tadeusz', 'Adam Mickiewicz', 1),
(2, 'Lalka', 'Bolesław Prus', 2),
(3, 'Potop', 'Sienkiewicz', 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orderbooks`
--

CREATE TABLE `orderbooks` (
  `ID` int(11) NOT NULL,
  `ordersID` int(11) NOT NULL,
  `booksID` int(11) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `orderbooks`
--

INSERT INTO `orderbooks` (`ID`, `ordersID`, `booksID`, `amount`) VALUES
(1, 2, 1, 5),
(2, 2, 2, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orders`
--

CREATE TABLE `orders` (
  `ID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `totalPrice` int(11) NOT NULL,
  `deliveryAddress` varchar(100) NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `orders`
--

INSERT INTO `orders` (`ID`, `userID`, `totalPrice`, `deliveryAddress`, `timestamp`) VALUES
(2, 2, 25, '34-700 Rdzawka 67', '2019-05-04 09:42:09');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`ID`, `username`, `password`, `email`, `address`) VALUES
(1, 'test', 'test', 'test@test.com', ''),
(2, 'test2', 'test2', 'test2@test.com', ''),
(3, 'test3', 'test3', 'test3@test', ''),
(4, 'test3', 'test3', 'test3@test', ''),
(5, 'test3', 'test3', 'test3@test', ''),
(6, 'admin', 'admin', 'email@admin.net', '');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `orderbooks`
--
ALTER TABLE `orderbooks`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `books`
--
ALTER TABLE `books`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `orderbooks`
--
ALTER TABLE `orderbooks`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `orders`
--
ALTER TABLE `orders`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
