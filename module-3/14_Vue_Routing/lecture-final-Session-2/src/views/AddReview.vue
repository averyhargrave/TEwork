<template>
    <div>
        <h1>Add Reviews</h1>
        <add-review />   <!-- use the AddReview.vue component-->
    </div>
</template>

<script>
import AddReview from '@/components/AddReview.vue'  // include the AddReview.vue code

export default {
    components: {
        AddReview   // Tell Vue we are using the AddReview component <add-review>
    }
}
</script>
