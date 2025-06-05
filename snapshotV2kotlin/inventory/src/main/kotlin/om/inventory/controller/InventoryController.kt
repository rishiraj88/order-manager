package om.inventory.controller

import om.inventory.dto.InventoryItemReq
import om.inventory.service.InventoryService
import org.springframework.http.CacheControl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/api/v2/inventory/")
class InventoryController(val inventoryService: InventoryService) {

    @GetMapping
    fun isInStock(@RequestBody inventoryItemReq: InventoryItemReq): ResponseEntity<Boolean> {
        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(30_000, TimeUnit.SECONDS))
            .body(inventoryService.isItemInStock(inventoryItemReq.skuCode, inventoryItemReq.quantityToCheck))
    }

}