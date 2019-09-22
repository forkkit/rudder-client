package com.rudderlabs.android.library.exceptions

import java.lang.Exception

class InvalidQueueSizeException(message: String) : Exception(message)

class InvalidUriException(message: String) : Exception(message)

class MalformedEventException(message: String) : Exception(message)