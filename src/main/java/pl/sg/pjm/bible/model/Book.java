package pl.sg.pjm.bible.model;

import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public enum Book {

    GENESIS(1, "Genesis", "Rodzaju", "RODZAJU",
            List.of(31, 25, 24, 26, 32, 22, 24, 22, 29, 32, 32, 20, 18, 24, 21, 16, 27, 33, 38, 18, 34, 24, 20, 67, 34, 35, 46, 22, 35, 43, 55, 32, 20, 31, 29, 43, 36, 30, 23, 23, 57, 38, 34, 34, 28, 34, 31, 22, 33, 26),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    EXODUS(2, "Exodus", "Wyj\u015Bcia", "WYJ\u015ACIA",
            List.of(22, 25, 22, 31, 23, 30, 25, 32, 35, 29, 10, 51, 22, 31, 27, 36, 16, 27, 25, 26, 36, 31, 33, 18, 40, 37, 21, 43, 46, 38, 18, 35, 23, 35, 35, 38, 29, 31, 43, 38),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    LEVITICUS(3, "Leviticus", "Kap\u0142a\u0144ska", "KAP\u0141A\u0143SKA",
            List.of(17, 16, 17, 35, 19, 30, 38, 36, 24, 20, 47, 8, 59, 57, 33, 34, 16, 30, 37, 27, 24, 33, 44, 23, 55, 46, 34),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    NUMBERS(4, "Numbers", "Liczb", "LICZB",
            List.of(54, 34, 51, 49, 31, 27, 89, 26, 23, 36, 35, 16, 33, 45, 41, 50, 13, 32, 22, 29, 35, 41, 30, 25, 18, 65, 23, 31, 40, 16, 54, 42, 56, 29, 34, 13),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    DEUTERONOMY(5, "Deuteronomy", "Powt\u00F3rzonego Prawa", "POWT\u00D3RZONEGO_PRAWA",
            List.of(46, 37, 29, 49, 33, 25, 26, 20, 29, 22, 32, 32, 18, 29, 23, 22, 20, 22, 21, 20, 23, 30, 25, 22, 19, 19, 26, 68, 29, 20, 30, 52, 29, 12),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    JOSHUA(6, "Joshua", "Jozuego", "JOZUEGO",
            List.of(18, 24, 17, 24, 15, 27, 26, 35, 27, 43, 23, 24, 33, 15, 63, 10, 18, 28, 51, 9, 45, 34, 16, 33),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    JUDGES(7, "Judges", "S\u0119dzi\u00F3w", "S\u0118DZI\u00D3W",
            List.of(36, 23, 31, 24, 31, 40, 25, 35, 57, 18, 40, 15, 25, 20, 20, 31, 13, 31, 30, 48, 25),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    RUTH(8, "Ruth", "Rut", "RUT",
            List.of(22, 23, 18, 22),
            List.of(0, 0, 0, 0)
    ),
    FIRST_SAMUEL(9, "1 Samuel", "1 Samuela", "1_SAMUELA",
            List.of(28, 36, 21, 22, 12, 21, 17, 22, 27, 27, 15, 25, 23, 52, 35, 23, 58, 30, 24, 42, 15, 23, 29, 22, 44, 25, 12, 25, 11, 31, 13),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    SECOND_SAMUEL(10, "2 Samuel", "2 Samuela", "2_SAMUELA",
            List.of(27, 32, 39, 12, 25, 23, 29, 18, 13, 19, 27, 31, 39, 33, 37, 23, 29, 33, 43, 26, 22, 51, 39, 25),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    FIRST_KINGS(11, "1 Kings", "1 Kr\u00F3l\u00F3w", "1_KR\u00D3L\u00D3W",
            List.of(53, 46, 28, 34, 18, 38, 51, 66, 28, 29, 43, 33, 34, 31, 34, 34, 24, 46, 21, 43, 29, 53),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    SECOND_KINGS(12, "2 Kings", "2 Kr\u00F3l\u00F3w", "2_KR\u00D3L\u00D3W",
            List.of(18, 25, 27, 44, 27, 33, 20, 29, 37, 36, 21, 21, 25, 29, 38, 20, 41, 37, 37, 21, 26, 20, 37, 20, 30),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    FIRST_CHRONICLES(13, "1 Chronicles", "1 Kronik", "1_KRONIK",
            List.of(54, 55, 24, 43, 26, 81, 40, 40, 44, 14, 47, 40, 14, 17, 29, 43, 27, 17, 19, 8, 30, 19, 32, 31, 31, 32, 34, 21, 30),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    SECOND_CHRONICLES(14, "2 Chronicles", "2 Kronik", "2_KRONIK",
            List.of(17, 18, 17, 22, 14, 42, 22, 18, 31, 19, 23, 16, 22, 15, 19, 14, 19, 34, 11, 37, 20, 12, 21, 27, 28, 23, 9, 27, 36, 27, 21, 33, 25, 33, 27, 23),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    EZRA(15, "Ezra", "Ezdrasza", "EZDRASZA",
            List.of(11, 70, 13, 24, 17, 22, 28, 36, 15, 44),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ),
    NEHEMIAH(16, "Nehemiah", "Nehemiasza", "NEHEMIASZA",
            List.of(11, 20, 32, 23, 19, 19, 73, 18, 38, 39, 36, 47, 31),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    ESTHER(17, "Esther", "Estery", "ESTERY",
            List.of(22, 23, 15, 17, 14, 14, 10, 17, 32, 3),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    JOB(18, "Job", "Hioba", "HIOBA",
            List.of(22, 13, 26, 21, 27, 30, 21, 22, 35, 22, 20, 25, 28, 22, 35, 22, 16, 21, 29, 29, 34, 30, 17, 25, 6, 14, 23, 28, 25, 31, 40, 22, 33, 37, 16, 33, 24, 41, 30, 24, 34, 17),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    PSALMS(19, "Psalms", "Psalmy", "PSALM",
            List.of(6, 12, 8, 8, 12, 10, 17, 9, 20, 18, 7, 8, 6, 7, 5, 11, 15, 50, 14, 9, 13, 31, 6, 10, 22, 12, 14, 9, 11, 12, 24, 11, 22, 22, 28, 12, 40, 22, 13, 17, 13, 11, 5, 26, 17, 11, 9, 14, 20, 23, 19, 9, 6, 7, 23, 13, 11, 11, 17, 12, 8, 12, 11, 10, 13, 20, 7, 35, 36, 5, 24, 20, 28, 23, 10, 12, 20, 72, 13, 19, 16, 8, 18, 12, 13, 17, 7, 18, 52, 17, 16, 15, 5, 23, 11, 13, 12, 9, 9, 5, 8, 28, 22, 35, 45, 48, 43, 13, 31, 7, 10, 10, 9, 8, 18, 19, 2, 29, 176, 7, 8, 9, 4, 8, 5, 6, 5, 6, 8, 8, 3, 18, 3, 3, 21, 26, 9, 8, 24, 13, 10, 7, 12, 15, 21, 10, 20, 14, 9, 6),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    PROVERBS(20, "Proverbs", "Przys\u0142\u00F3w", "PRZYS\u0141\u00D3W",
            List.of(33, 22, 35, 27, 23, 35, 27, 36, 18, 32, 31, 28, 25, 35, 33, 33, 28, 24, 29, 30, 31, 29, 35, 34, 28, 28, 27, 28, 27, 33, 31),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    ECCLESIASTES(21, "Ecclesiastes", "Kaznodziei", "KAZNODZIEI",
            List.of(18, 26, 22, 16, 20, 12, 29, 17, 18, 20, 10, 14),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    SONG_OF_SOLOMON(22, "Song of Solomon", "Pie\u015B\u0144 nad Pie\u015Bniami", "PIE\u015A\u0143_NAD_PIE\u015ANIAMI",
            List.of(17, 17, 11, 16, 16, 13, 13, 14),
            List.of(0, 0, 0, 0, 0, 0, 0, 0)),
    ISAIAH(23, "Isiah", "Izajasza", "IZAJASZA",
            List.of(31, 22, 26, 6, 30, 13, 25, 22, 21, 34, 16, 6, 22, 32, 9, 14, 14, 7, 25, 6, 17, 25, 18, 23, 12, 21, 13, 29, 24, 33, 9, 20, 24, 17, 10, 22, 38, 22, 8, 31, 29, 25, 28, 28, 25, 13, 15, 22, 26, 11, 23, 15, 12, 17, 13, 12, 21, 14, 21, 22, 11, 12, 19, 12, 25, 24),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    JEREMIAH(24, "Jeremiah", "Jeremiasza", "JEREMIASZA",
            List.of(19, 37, 25, 31, 31, 30, 34, 22, 26, 25, 23, 17, 27, 22, 21, 21, 27, 23, 15, 18, 14, 30, 40, 10, 38, 24, 22, 17, 32, 24, 40, 44, 26, 22, 19, 32, 21, 28, 18, 16, 18, 22, 13, 30, 5, 28, 7, 47, 39, 46, 64, 34),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    LAMENTATIONS(25, "Lamentations", "Lamentacje", "LAMENTACJE",
            List.of(22, 22, 66, 22, 22),
            List.of(0, 0, 0, 0, 0)),
    EZEKIEL(26, "Ezekiel", "Ezechiela", "EZECHIELA",
            List.of(28, 10, 27, 17, 17, 14, 27, 18, 11, 22, 25, 28, 23, 23, 8, 63, 24, 32, 14, 49, 32, 31, 49, 27, 17, 21, 36, 26, 21, 26, 18, 32, 33, 31, 15, 38, 28, 23, 29, 49, 26, 20, 27, 31, 25, 24, 23, 35),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    DANIEL(27, "Daniel", "Daniela", "DANIELA",
            List.of(21, 49, 30, 37, 31, 28, 28, 27, 27, 21, 45, 13),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    HOSEA(28, "Hoshea", "Ozeasza", "OZEASZA",
            List.of(11, 23, 5, 19, 15, 11, 16, 14, 17, 15, 12, 14, 16, 9),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    JOEL(29, "Joel", "Joela", "JOELA",
            List.of(20, 32, 21),
            List.of(0, 0, 0)),
    AMOS(30, "Amos", "Amosa", "AMOSA",
            List.of(15, 16, 15, 13, 27, 14, 17, 14, 15),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0)),
    OBADIAH(31, "Obadiah", "Abdiasza", "ABDIASZA",
            List.of(21),
            List.of(0)),
    JONAH(32, "Jonah", "Jonasza", "JONASZA",
            List.of(17, 10, 10, 11),
            List.of(0, 0, 0, 0)),
    MICAH(33, "Micah", "Micheasza", "MICHEASZA",
            List.of(16, 13, 12, 13, 15, 16, 20),
            List.of(0, 0, 0, 0, 0, 0, 0)),
    NAHUM(34, "Nahum", "Nahuma", "NAHUMA",
            List.of(15, 13, 19),
            List.of(0, 0, 0)),
    HABAKKUK(35, "Habakkuk", "Habakuka", "HABAKUKA",
            List.of(17, 20, 19),
            List.of(0, 0, 0)),
    ZEPHANIAH(36, "Zephaniah", "Sofoniasza", "SOFONIASZA",
            List.of(18, 15, 20),
            List.of(0, 0, 0)),
    HAGGAI(37, "Haggai", "Aggeusza", "AGGEUSZA",
            List.of(15, 23),
            List.of(0, 0)),
    ZECHARIAH(38, "Zechariah", "Zachariasza", "ZACHARIASZA",
            List.of(21, 13, 10, 14, 11, 15, 14, 23, 17, 12, 17, 14, 9, 21),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    MALACHI(39, "Malachi", "Malachiasza", "MALACHIASZA",
            List.of(14, 17, 18, 6),
            List.of(0, 0, 0, 0)),
    MATTHEW(40, "Matthew", "Mateusza", "MATEUSZA",
            List.of(25, 23, 17, 25, 48, 34, 29, 34, 38, 42, 30, 50, 58, 36, 39, 28, 27, 35, 30, 34, 46, 46, 39, 51, 46, 75, 66, 20),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0)),
    MARK(41, "Mark", "Marka", "MARKA",
            List.of(45, 28, 35, 41, 43, 56, 37, 38, 50, 52, 33, 44, 37, 72, 47, 8),
            List.of(0, 0, 0, 0, 0, 0, 1, 0, 2, 0, 1, 0, 0, 0, 1, 0)),
    LUKE(42, "Luke", "\u0141ukasza", "\u0141UKASZA",
            List.of(80, 52, 38, 44, 39, 49, 50, 56, 62, 42, 54, 59, 35, 35, 32, 31, 37, 43, 48, 47, 38, 71, 56, 53),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0)),
    JOHN(43, "John", "Jana", "JANA",
            List.of(51, 25, 36, 54, 47, 71, 53, 59, 41, 42, 57, 50, 38, 31, 27, 33, 26, 40, 42, 31, 25),
            List.of(0, 0, 0, 0, 1, 0, 1, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    ACTS(44, "Acts", "Dzieje Apostolskie", "DZIEJE",
            List.of(26, 47, 26, 37, 42, 15, 60, 40, 43, 48, 30, 25, 52, 28, 41, 40, 34, 28, 41, 38, 40, 30, 35, 27, 27, 32, 44, 31),
            List.of(0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1)),
    ROMANS(45, "Romans", "Rzymian", "RZYMIAN",
            List.of(32, 29, 31, 25, 21, 23, 25, 39, 33, 21, 36, 21, 14, 23, 33, 27),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1)),
    FIRST_CORINTHIANS(46, "1 Corinthians", "1 Koryntian", "1_KORYNTIAN",
            List.of(31, 16, 23, 21, 13, 20, 40, 13, 27, 33, 34, 31, 13, 40, 58, 24),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    SECOND_CORINTHIANS(47, "2 Corinthians", "2 Koryntian", "2_KORYNTIAN",
            List.of(24, 17, 18, 18, 21, 18, 16, 24, 15, 18, 33, 21, 14),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    GALATIANS(48, "Galatians", "Galat\u00F3w", "GALAT\u00D3W",
            List.of(24, 21, 29, 31, 26, 18),
            List.of(0, 0, 0, 0, 0, 0)),
    EPHESIANS(49, "Ephesians", "Efezjan", "EFEZJAN",
            List.of(23, 22, 21, 32, 33, 24),
            List.of(0, 0, 0, 0, 0, 0)),
    PHILIPPIANS(50, "Philippians", "Filipian", "FILIPIAN",
            List.of(30, 30, 21, 23),
            List.of(0, 0, 0, 0)),
    COLOSSIANS(51, "Colossians", "Kolosan", "KOLOSAN",
            List.of(29, 23, 25, 18),
            List.of(0, 0, 0, 0)),
    FIRST_THESSALONIANS(52, "1 Thessalonians", "1 Tesaloniczan", "1_TESALONICZAN",
            List.of(10, 20, 13, 18, 28),
            List.of(0, 0, 0, 0, 0)),
    SECOND_THESSALONIANS(53, "2 Thessalonians", "2 Tesaloniczan", "2_TESALONICZAN",
            List.of(12, 17, 18),
            List.of(0, 0, 0)),
    FIRST_TIMOTHY(54, "1 Timothy", "1 Tymoteusza", "1_TYMOTEUSZA",
            List.of(20, 15, 16, 16, 25, 21),
            List.of(0, 0, 0, 0, 0, 0)),
    SECOND_TIMOTHY(55, "2 Timothy", "2 Tymoteusza", "2_TYMOTEUSZA",
            List.of(18, 26, 17, 22),
            List.of(0, 0, 0, 0)),
    TITUS(56, "Titus", "Tytusa", "TYTUSA",
            List.of(16, 15, 15),
            List.of(0, 0, 0)),
    PHILEMON(57, "Philemon", "Filemona", "FILEMONA",
            List.of(25),
            List.of(0)),
    HEBREWS(58, "Hebrews", "Hebrajczyk\u00F3w", "HEBRAJCZYK\u00D3W",
            List.of(14, 18, 19, 16, 14, 20, 28, 13, 28, 39, 40, 29, 25),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    JAMES(59, "James", "Jakuba", "JAKUBA",
            List.of(27, 26, 18, 17, 20),
            List.of(0, 0, 0, 0, 0)),
    FIRST_PETER(60, "1 Peter", "1 Piotra", "1_PIOTRA",
            List.of(25, 25, 22, 19, 14),
            List.of(0, 0, 0, 0, 0)),
    SECOND_PETER(61, "2 Peter", "2 Piotra", "2_PIOTRA",
            List.of(21, 22, 18),
            List.of(0, 0, 0)),
    FIRST_JOHN(62, "1 John", "1 Jana", "1_JANA",
            List.of(10, 29, 24, 21, 21),
            List.of(0, 0, 0, 0, 0)),
    SECOND_JOHN(63, "2 John", "2 Jana", "2_JANA",
            List.of(13),
            List.of(0)),
    THIRD_JOHN(64, "3 John", "3 Jana", "3_JANA",
            List.of(14),
            List.of(0)),
    JUDE(65, "Jude", "Judy", "JUDY",
            List.of(25),
            List.of(0)),
    REVELATION(66, "Revelation", "Objawienie", "OBJAWIENIE",
            List.of(20, 29, 22, 11, 14, 17, 17, 13, 21, 11, 19, 17, 18, 20, 8, 21, 18, 24, 21, 15, 27, 21),
            List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

    public static Book fromFileName(Path file) {
        Pattern bookNum = Pattern.compile("nwt_(\\d*)");
        Matcher matcher = bookNum.matcher(file.getFileName().toString());
        if (matcher.find()) {
            int booknum = Integer.parseInt(matcher.group(1));
            return Stream.of(Book.values()).filter(b -> b.getOrder() == booknum).findAny().orElseThrow();
        } else {
            throw new NullPointerException();
        }
    }

    private final int order;
    private final String englishName;
    private final String polishName;
    private final String verseInFileName;
    private final List<Integer> verses;
    private final List<Integer> skippedVersesInNWT;

    Book(int order, String englishName, String polishName, String verseInFileName, List<Integer> verses, List<Integer> skippedVersesInNWT) {
        this.order = order;
        this.englishName = englishName;
        this.polishName = polishName;
        this.verseInFileName = verseInFileName;
        this.verses = verses;
        this.skippedVersesInNWT = skippedVersesInNWT;
    }

    public int getOrder() {
        return order;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getPolishName() {
        return polishName;
    }

    public String getVerseInFileName() {
        return verseInFileName;
    }

    public List<Integer> getVerses() {
        return verses;
    }

    public boolean isNewTestament() {
        return order >= 40;
    }

    public List<Integer> getSkippedVersesInNWT() {
        return skippedVersesInNWT;
    }
}
