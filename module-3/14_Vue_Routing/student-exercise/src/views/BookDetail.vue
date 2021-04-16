<template>
    <div class="BookDetails">
        <p>   {{book.title}}     </p>
        <p>   {{book.author}}    </p>
        <img v-if="book.isbn" v-bind:src="'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'" />
        <p v-if="book.read === true"> I have read this book   </p>
        <p v-else>  I have not read this book.  </p>
        <h1>Book Details</h1>
    </div>
</template>

<script>
export default ({
    name: 'BookDetail',
    computed: {
        book() {
            return this.$store.state.books.filter(aBook => {
                return aBook.isbn === this.$route.params.isbn;
            })[0]
        }
    }
})
</script>

<style scoped>
</style>