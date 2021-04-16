<template>
    <div>
        <h1>{{ hotel.name }} Details</h1>

        <div>
           <router-link to="/">Return to Home</router-link>
        </div> 
    </div>
         
</template>
<script>

export default {
    name: "hotel-detail",
       data() {
        return { 
            /* Start of temporary data for testing purposes */
            hotel: {
                     hotelID: 1,
                     name: "The Squirrel Den"
                    }
            /* End of temporary data for testing purposes   */
        } 

    }
}
</script>
<style scoped>

</style>