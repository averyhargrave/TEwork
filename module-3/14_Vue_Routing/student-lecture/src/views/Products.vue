<!-- Router view for displaying the list of products on our main page -->
<template>
    <div class='products'> <!-- this is the only div allowed in the template -->
        <h1>Products</h1>
        <products-list />  <!-- Vue component to list the products on the page -->
    </div>
</template>

<script>
import ProductsList from '@/components/ProductsList' // @ - shorthand for the root folder for the app (/src)

export default {
    components: {
        ProductsList
    }
}
</script>

<style scoped>
</style>
