<template>
    <div class="myBooks">
        <h1>My Books</h1>
        <reading-list/>
    </div>
</template>

<script>
import ReadingList from '@/components/ReadingList.vue'

export default {
    name: 'MyBooks',
    components: {
        ReadingList
    }
}
</script>

<style scoped>
</style>