package om.product.exception

class ResourceNotFoundException : Exception() {
    fun getMessage(): String = super.message ?: ""
}