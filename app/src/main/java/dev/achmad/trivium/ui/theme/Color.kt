package dev.achmad.trivium.ui.theme

import androidx.compose.ui.graphics.Color

val splashBackground = Color(0xFF3DDC84)

// Base Background Colors (existing)
val background20 = Color(0xFF44515e)   // Lightest background
val background40 = Color(0xFF384553)   // Light background
val background60 = Color(0xFF2d3a49)   // Medium background
val background80 = Color(0xFF253240)   // Dark background
val background100 = Color(0xFF1a2632)  // Darkest background

// Primary Colors
val primary = Color(0xFF64B5F6)        // Main action color (buttons, labels)
val primaryLight = Color(0xFF90CAF9)    // Hover states, highlights
val primaryDark = Color(0xFF4298E5)     // Pressed states

// Secondary Colors (Text and Content)
val secondary = Color(0xFFE3E8ED)       // Main text color
val secondaryLight = Color(0xFFF5F7F9)  // High emphasis text
val secondaryDark = Color(0xFFCCD2D8)   // Lower emphasis text

// Accent Colors (Highlights and Special Elements)
val accent = Color(0xFFA363FF)          // Orange accent for important elements
val accentLight = Color(0xFFBE8AFF)     // Lighter version for hover states
val accentDark = Color(0xFF7E48DB)      // Darker version for pressed states

// Disabled States
val disabled = Color(0xFF9AA0A6)        // Disabled button/element background
val disabledText = Color(0xFFBDC1C6)    // Disabled text

// Status Colors
val success = Color(0xFF66BB6A)         // Success states (correct answers)
val error = Color(0xFFEF5350)           // Error states (wrong answers)
val warning = Color(0xFFFFCA28)         // Warning states
val info = Color(0xFF29B6F6)            // Information states

// Semantic Colors
val timerNormal = Color(0xFF64B5F6)     // Normal timer state
val timerWarning = Color(0xFFFFB74D)    // Warning timer state (< 30s)
val timerUrgent = Color(0xFFEF5350)     // Urgent timer state (< 10s)

// Overlay Colors
val scrim = Color(0x80000000)           // Dark overlay for modals (50% opacity)
val highlight = Color(0x1AFFFFFF)        // Subtle highlight (10% white)
val shadow = Color(0x40000000)          // Subtle shadow (25% opacity)

// Text Variations
val textPrimary = secondary             // Main text
val textSecondary = Color(0xFFB0B8BF)   // Secondary text
val textTertiary = Color(0xFF878D96)    // Tertiary text
val textOnPrimary = Color(0xFFFFFFFF)   // Text on primary colored backgrounds


