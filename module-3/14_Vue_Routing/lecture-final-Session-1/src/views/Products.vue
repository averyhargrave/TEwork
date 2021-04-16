<!-- Router view for displaying the list of products on our main page -->
<template> <!-- contains the html for rthe page -->
    <div class='products'>  <!-- this the one and only div allowed in the template --> 
        <h1>Products</h1>
        <products-list />   <!-- Vue component to list the products on the page -->
    </div>
    
</template>
<script> // contains the JavaScript for the page
 // The '@' is shorthand for the root folder for the app (/src)
 //                     /src/components/ProductsList would be OK too
 import ProductsList from '@/components/ProductsList'

export default {
components: {
    ProductsList
}
}
</script>

<style scoped></style>  