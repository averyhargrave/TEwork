<template>
    <div class="NewBook">
        <h1>New Book</h1>
        <new-book-form/>
    </div>
</template>

<script>
import NewBookForm from '@/components/NewBookForm.vue'

export default ({
    name: 'NewBook',
    components: {
        NewBookForm
    }
})
</script>

<style scoped>
</style>