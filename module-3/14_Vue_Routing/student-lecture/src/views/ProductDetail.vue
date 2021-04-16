<template>
    <div id='app' class='main'>
        <h1>{{product.name}}</h1>
        <p class='description'>{{product.description}}</p>
        <div class='well-display'> <!-- display the summary of ratings -->
            <average-summary/>
            <star-summary rating='1'/>
            <star-summary rating='2'/>
            <star-summary rating='3'/>
            <star-summary rating='4'/>
            <star-summary rating='5'/>
            <review-list/>
        </div> 
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
    computed: { // run these functions to create attributes when something on the page changes
        product() {
            return this.$store.state.products // get the data from the products array datastore
        }
    }
}
</script>

<style scoped>
</style>
