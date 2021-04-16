<template>
    <div id='app' class='main'> 
        <h1> {{product.name}}</h1>
        <p class='description'>{{product.description}}</p>
        <div class='well-display'> <!-- display teh summary of ratings -->
            <average-summary />
            <star-summary rating="1" />
            <star-summary rating="2" />
            <star-summary rating="3" />
            <star-summary rating="4" />
            <star-summary rating="5" />
        </div>
        <review-list />
    </div>
</template>
<script>
import AverageSummary from '@/components/AverageSummary.vue'
import StarSummary    from '@/components/StarSummary.vue'
import ReviewList     from '@/components/ReviewList.vue'

export default {
    name: 'product-detail',
    components: {
        AverageSummary,
        StarSummary,
        ReviewList
    },
    // We need to get the product id that was selected from the id path variable 
    // before the html for the page is created by Vue because we only want the reviews for the selected product 
    created() {  // the created functions are run before the html is compiled by Vue
        // define a variable to hold the value from the id path variable
        // $route.params.variable-name is now you reference path variables in the URL
        const currentProductId = this.$route.params.id  // get the value of the id variable in the URL

        // Because we need to share this with other components, we need to store in the data store
        // the data store has mutation called 'SET_ACTIVE_PRODUCT' to save the current product id
        this.$store.commit('SET_ACTIVE_PRODUCT', currentProductId)
    },
    computed: {  // run these functions to create attributes when something on the page changes
        product() {
            // We only want the reviews from the data store for the product selected by the user
            // the activeProduct attribute in the data store hold the id of the current product
            
            // .find() array method will create a new array from elements a function tells it you want
            // .find((currElemName) => { return true id current element should be in the new array})
            
            // use .find() to create a array from the products array in teh data store 
            // containing only the elements that an id that matches the activeProduct in tha data store
            return this.$store.state.products.find(
                        (aProduct) => {return aProduct.id == this.$store.state.activeProduct}   
            )

        }
    }  // end of computed section
} // end of export

</script>
